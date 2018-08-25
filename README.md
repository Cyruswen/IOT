## 云享开关
#### 主要技术：
C语言, socket编程, MySQL, HTTP协议, TCP协议,多线程, html, Android, NodeMcu，lua
#### 主要功能：
利用Android手机App以及自主HTTP服务器、TCP服务器和NodeMcu实现用户登录、远程控制家用插座开关等功能。
#### 过程简述：
1.开发Android App，实现简单的登录以及开关控制的功能。
2.利用socket套接字编程实现可处理get或post请求的多线程HTTP服务器。
- 利用CGI程序连接MySQL数据库，处理App的登录请求。
- 利用CGI程序将App的开关控制命令以及设备id转发给TCP服务器。
3.利用socket套接字编程实现可以与NodeMcu进行长连接的多线程TCP服务器
- 利用顺序表对来自NodeMcu连接进行维护(设备id以及连接套接字)
- 通过HTTP发来的设备id在顺序表查找到对应的连接套接字，并将命令转发给NodeMcu.
- NodeMcu解析命令控制I/O口输出，通过继电器控制插板开关。
