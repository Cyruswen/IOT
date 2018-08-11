#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <stdlib.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#define MAX 1024
//enen

int main(int argc, char *argv[]){
    if(argc != 3){
        printf("Usage: %s [ip] [port]\n",argv[0]);
        return 1;
    }
    int sock = socket(AF_INET, SOCK_STREAM, 0);
    if(sock < 0){
        printf("socket error!\n");
        return 2;
    }

    struct sockaddr_in service;
    service.sin_family = AF_INET;
    service.sin_port = htons(atoi(argv[2]));
    service.sin_addr.s_addr = inet_addr(argv[1]);

    if(connect(sock, (struct sockaddr*)&service, sizeof(service)) < 0){
        printf("connect error!\n");
        return 3;
    }
    
    ssize_t s = 0;
    char buf[] = "mcxjkui connect 2";
    write(sock, buf, sizeof buf);
    s = read(sock, buf, 15);
    printf("buf:%s\n", buf);
    close(sock);
    return 0;
}
