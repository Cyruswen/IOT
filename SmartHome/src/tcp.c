#include <stdio.h>
#include <ctype.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <pthread.h>
#include <sys/wait.h>

#define MAXSIZE 1024

typedef struct Arg{
    int fd;
    struct sockaddr_in addr;
} Arg;

void Usage()
{
    printf("Usage:./server port\n");
}

void getline(int client_fd)
{
    char source[MAXSIZE/4] = {0};
    char directive[MAXSIZE/4] = {0};
    char buf[MAXSIZE] = {0};
    ssize_t read_size = read(client_fd, buf, sizeof buf);
    if(read_size == 0)
    {
        close(client_fd);
        return;
    }
    size_t i = 0;
    size_t j = 0;
    while(!isspace(buf[i]) && i < sizeof(buf) && i < sizeof(source))
    {
        source[j++] = buf[i++];
    }
    source[j] = '\0';
    printf("%s\n", source);
    //走到这儿，将客户端确定
    while(isspace(buf[i]) && i < sizeof(buf))
    {
        i++;
    }
    j = 0;
    while(buf[i] != '\0')
    {
        directive[j++] = buf[i++];
    }
    directive[j] = '\0';
    printf("%s\n", directive);
}

void ProcessRequest(int client_fd, struct sockaddr_in* client_addr){
    char buf[1024] = {0};
    for(;;){
        //TODO
        ssize_t read_size = read(client_fd, buf, sizeof(buf));
        if(read_size < 0){
            perror("read");
            continue;
        }
        //inet_ntoa 将十进制网络序列转换为点分十进制
        if(read_size == 0){
            printf("client %s say goodbye!\n", inet_ntoa(client_addr->sin_addr));
            close(client_fd);
            break;
        }
        buf[read_size] = '\0';
        printf("client: %s say: %s\n", inet_ntoa(client_addr->sin_addr), buf);
        write(client_fd, buf, strlen(buf));
    }
    return;
}


void* CreateWorker(void* ptr )
{
    Arg* arg = (Arg*)ptr;
    ProcessRequest(arg->fd, &arg->addr);
    free(arg);
}

int main(int argc,char* argv[])
{
    if(argc != 2)
    {
        Usage();
        return -1;
    }
    struct sockaddr_in addr;
    addr.sin_family = AF_INET;
    addr.sin_port = htons(atoi(argv[1]));
    addr.sin_addr.s_addr = INADDR_ANY; 
    //创建套接字
    int fd = socket(AF_INET,SOCK_STREAM, 0);
    if(fd < 0)
    {
        perror("socket");
        return -2;
    }
    //绑定套接字
    int ret = bind(fd, (struct sockaddr*)&addr, sizeof(addr));
    if(ret < 0)
    {
        perror("bind");
        return -3;
    }
    //监听套接字
    ret = listen(fd, 10); 
    if(ret < 0)
    {
        perror("listen");
        return -4;
    }
    //接收套接字
    for(;;)
    {
        struct sockaddr_in client_addr;
        socklen_t len = sizeof(client_addr);
        int client_fd = accept(fd, (struct sockaddr*)&client_fd, &len);
        if(client_fd < 0)
        {
            perror("accept");
            continue;
        }
        pthread_t tid;
        Arg* arg = (Arg*)malloc(sizeof(Arg));
        arg->fd = client_fd;
        arg->addr = client_addr;
        pthread_create(&tid, NULL, CreateWorker, (void*)arg);
        pthread_detach(tid);
    }
    return 0;
}

