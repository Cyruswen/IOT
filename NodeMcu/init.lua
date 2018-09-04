print("set up wifi mode")
wifi.setmode(wifi.STATION)
wifi.sta.config("HTTP/1.1 200 OK","960812min")
wifi.sta.connect()
tmr.alarm(1, 1000, 1, function()
if wifi.sta.getip()== nil then
print("IP unavaiable, Waiting...")
else
tmr.stop(1)
print("Config done, IP is "..wifi.sta.getip())
dofile("tcpclient.lua")
end
end)
