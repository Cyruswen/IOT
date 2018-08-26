#include <stdio.h>
#include <iostream>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <netinet/in.h>
#include <string>
using namespace std;

#define SERVER_IP "192.168.43.24"
#define SERVER_PORT 9999
#define BUF_SIZE 1024




int client(char* arg)
{
    struct sockaddr_in addr;
    addr.sin_family = AF_INET;
    if(inet_aton(SERVER_IP, &addr.sin_addr) == 0)
    {
        perror("inet_aton");

    }
    addr.sin_port = htons((SERVER_PORT));

    int fd = socket(AF_INET, SOCK_STREAM, 0);
    if(fd == -1)
    {
        perror("socket");
        return -1;

    }
    if(connect(fd, (struct sockaddr*)&addr, sizeof addr) == -1)
    {
        perror("connect");
        return -1;

    }

    // 解析 arg 里面的请求数据
         // 然后发给 tcp 服务器 
    char buf[BUF_SIZE] = {0};
    std::string ver;
    std::string pin;
    //app=ON1 
    ver = strtok(arg, "=");
    pin = strtok(NULL, "=");

    std::string msg("");
    msg += ver;
    msg += " ";
    msg += "1001";  //这里假装有调试用
    msg += " ";
    msg += pin;
    // msg = "app 1 ON1"
    // 发送
    if(write(fd, msg.c_str(), msg.size()) == -1)
    {
        perror("write");
        return 404;
    }

    // 接收
    ssize_t read_size = read(fd, buf, sizeof(buf)-1);
    if(read_size == -1)
    {
        perror("read");
    }
    if(read_size == 0)
    {
        printf("server busy");
    }
    buf[read_size] = '\0';
    // 收到 tcp服务器的回应, 根据回应判断是否控制成功
    // 再将结果返回给 app 客户端
    write(1, buf, sizeof(buf));
    return 0;
}

int main()
{
    FILE *fp = fopen("/tmp/client.log", "a");
    char* method = NULL;
    char* query_string = NULL;
    char* arg = NULL;
    int content_len = -1;
    char buf[BUF_SIZE];
    if(getenv("METHOD") != NULL)
    {
        method = getenv("METHOD");
        fprintf(fp, "METHOD: %s\n", method);
        fflush(fp);
        if(strcasecmp(method, "GET") == 0)
        {
            if(getenv("QUERY_STRING") != NULL)
            {
                query_string = getenv("QUERY_STRING");
                fprintf(fp, "query_string: %s\n", query_string);
                fflush(fp);
                arg = query_string;
            }
        }
        else
        {
            // post
            if(getenv("CONTENT_LENGTH") != NULL)
            {
                content_len = atoi(getenv("CONTENT_LENGTH"));
                int i = 0;
                for(; i < content_len; i++)
                {
                    read(0, &buf[i], 1);
                }
                buf[i] = '\0';
                arg = buf;
            }
        }
        // 此时 buf 里存的就是 app 发来的请求
        // 然后把 buf 传给 client
        // 由client解析请求数据, 根据app的请求, 再向 tcp 服务器发送请求, 控制终端
        client(arg);
    }
    fclose(fp);
}
