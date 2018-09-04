DEVICE = "mcu"
ID  = "1001"
STATUS = "open"
host     = "101.200.63.71"
port     = 9999
LED      = 0
gpio.mode(LED,gpio.OUTPUT)

local function run()
  local cu = net.createConnection(net.TCP)
  cu:on("receive", function(cu, c) 
	print(c)
    if (c=="ON\0") then   
      gpio.write(LED, gpio.LOW)
    end
    if (c=="OFF") then   
      gpio.write(LED, gpio.HIGH)
    end
  end)
  cu:on('disconnection',function(scu)
    cu = nil
    tmr.stop(1)
    tmr.alarm(6, 5000, 0, run)
  end)
  
  cu:connect(port, host)
  cu:send(DEVICE.." "..ID.." "..STATUS)
end
run()