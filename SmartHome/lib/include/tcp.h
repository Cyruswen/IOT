#include <stdio.h>
#include <iostream>
#include <ctype.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <pthread.h>
#include <sys/wait.h>
#include <vector>
#define MAXSIZE 1024

typedef struct sockid{
    int id;
    int client_fd;
} sockid;

typedef struct Arg{
    int fd;
    struct sockaddr_in addr;
    std::vector<sockid> Mq;
} Arg;

int getline(int client_fd, char* buf, char* source, char* id,  char* directive);

int findSockId(int id, std::vector<sockid> mq);

void ProcessRequest(int client_fd, std::vector<sockid> mq);

void* CreateWorker(void* ptr);
