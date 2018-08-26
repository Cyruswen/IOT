DEVICE = "mcu"
ID  = "1001"
STATUS = "open"
host     = "192.168.81.132"
port     = 9999
LED      = 0
gpio.mode(LED,gpio.OUTPUT)

local function run()
  local cu = net.createConnection(net.TCP)
  cu:on("receive", function(cu, c) 
    print(c)
    r = cjson.decode(c)
    if r.M == "ON" then   
      gpio.write(LED, gpio.HIGH)  
      ok, played = pcall(cjson.encode, {M="mcu",ID=r.ID,C="open"})
      cu:send( played.."\n" )
    end
    if r.M == "OFF" then   
      gpio.write(LED, gpio.LOW)
      ok, stoped = pcall(cjson.encode, {M="mcu",ID=r.ID,C="off"})
      cu:send( stoped.."\n" ) 
    end
  end)
  cu:on('disconnection',function(scu)
    cu = nil
    tmr.stop(1)
    tmr.alarm(6, 5000, 0, run)
  end)
  cu:connect(9999, "192.168.43.24")
  --ok, s = pcall(cjson.encode, (DEVICE.." "..ID.." "..STATUS))
  --if ok then
  --  print(s)
 -- else
  --  print("failed to encode!")
 -- end
  cu:send(DEVICE.." "..ID.." "..STATUS)
  --tmr.alarm(1, 60000, 1, function()
  --cu:send(DEVICE.." "..ID.." "..STATUS)
  --end)
end
run()