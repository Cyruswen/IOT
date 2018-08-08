#pragma once
#include <stdio.h>
#include <unistd.h>
#include <string.h>
#include <stdlib.h>
#include <arpa/inet.h>
#include <sys/wait.h>
#include <sys/socket.h>
#include <fcntl.h>
#include <sys/sendfile.h>
#include <netinet/in.h>
#include <sys/types.h>
#include <pthread.h>
#include <ctype.h>
#include <strings.h>
#include <sys/stat.h>
#include <pthread.h>

#define MAXSIZE 1024
#define HOMEPAGE "index/index.html"

static int StartUp(int port);
int get_line(int sock, char* buf, int size);
void clear_header(int sock);
int exe_cgi(int sock, char path[], char method[], char* cur_url);
void status_response(int sock, int status_code);
int echo_www(int sock, const char* path, int size);
void accept_request(int sock);
static void* handle_client(void* arg);
