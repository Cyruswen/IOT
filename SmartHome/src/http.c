/*--------------------------------------------------------------------------------------
 * 项目名称：多线程http服务器
 * 功能描述：响应客户端发来的http请求，该版本支持get和post请求
 * 创建时间：2018年5月25日
 * 作者：温凯凯
 * 邮箱：Cyrus_wen@163.com
 *--------------------------------------------------------------------------------------*/

#include "http.h"
////首先设置结构体关键字
//创建套接字
//绑定套接字
//监听套接字
//接收套接字
//创建多线程处理套接字（分析接收到的套接字的
//http协议中的方法。）
const char* status_line =  "HTTP/1.0 200 OK\r\n";
const char* blank_line = "\r\n";

void usage(){
    printf("usage:./server [port]\n");
}

static int StartUp(int port){
    int opt = 1;
    struct sockaddr_in addr;
    addr.sin_family = AF_INET;
    addr.sin_port = htons(port);
    //不用输ip
    addr.sin_addr.s_addr = INADDR_ANY;
    int fd = socket(AF_INET, SOCK_STREAM, 0);
    if(fd < 0){
        perror("socket");
        exit(-2);
    }
    setsockopt(fd, SOL_SOCKET, SO_REUSEADDR, &opt, sizeof(opt));
    if(bind(fd, (struct sockaddr*)&addr, sizeof(addr)) < 0){
        perror("bind");
        exit(-3);
    }
    if(listen(fd, 100) < 0){
        perror("listen");
        exit(-4);
    }
    return fd;
}

int get_line(int sock, char* buf, int size){
    if(buf == NULL){
        return -1;
    }
    //如果s的发送缓冲中没有数据或者数据被协议成功发送完毕后，recv先检查套
    //接字s的接收缓冲区，如果s接收缓冲区中没有数据或者协议正在接收数据，那
    //么recv就一直等待，直到协议把数据接收完毕。当协议把数据接收完毕，recv
    //函数就把s的接收缓冲中的数据copy到buf中（注意协议接收到的数据可能大于
    //buf的长度，所以在这种情况下要调用几次recv函数才能把s的接收缓冲中的数
    //据copy完。recv函数仅仅是copy数据，真正的接收数据是协议来完成的）；
    //recv函数的返回值为实际copy的字节数
    //MSG_PEEK 窥看外来消息。
    int i = 0;
    ssize_t _s = -1;
    char ch = '\0';
    while(i < size - 1 && ch != '\n'){
        _s = recv(sock, &ch, 1, 0);
        if(_s > 0){
            if(ch == '\r')
            {
                _s=recv(sock, &ch, 1, MSG_PEEK);
                if(_s <= 0)
                {
                    return -1;
                }
                else
                {
                    if(ch == '\n')
                    {
                        _s = recv(sock, &ch, 1, 0);
                        if(_s <= 0)
                        {
                            return -1;
                        }
                    }
                    else
                    {
                        ch = '\n';
                    }
                }
            }
            buf[i++] = ch;
        }
    }
    buf[i] = '\0';
    return i;
}


void clear_header(int sock){
    char line[MAXSIZE];
    do{
        get_line(sock, line, sizeof(line));
    }while(strcmp(line, "\n") != 0);
}

int exe_cgi(int sock, char path[], char method[], char* cur_url){
    char line[MAXSIZE];
    int content_length = -1;
    char method_env[MAXSIZE/32];
    char quer_string_env[MAXSIZE];
    char content_length_env[MAXSIZE/16];

    if(strcasecmp(method, "GET") == 0){
        clear_header(sock);
    }
    else
    {
        //post
        while(strcmp(line, "\n") != 0){
            //找到正文长度
            if(strncmp(line, "Content-length: ", 16) == 0){
                //找到并将正文长度保存起来
                content_length = atoi(line + 16);
            }
            get_line(sock, line, sizeof(line)); 
        }
        if(content_length == -1){
            return 404;
        }
    }
    //发送时加上报头
    sprintf(line, "HTTP/1.0 200 OK\r\n");
    send(sock, line, strlen(line), 0);
    sprintf(line, "Content-Type: text/html\r\n");  
    send(sock, line, strlen(line), 0);
    sprintf(line, "\r\n");
    send(sock, line, strlen(line), 0);
    

    //创建两个管道（父进程需要知道子进程的执行结果，子进程需要参数，所以要进行进程间通信）
    //input -- 父进程将参数传给子进程
    //output -- 子进程将执行结果传给父进程
    int input[2];
    int output[2];//0表示读端 1表示写端
    if(pipe(input) < 0 || pipe(output) < 0)
    {
        perror("fork");
        return 404;
    }

    pid_t pid = fork();
    if(pid < 0){
        return 404;
    }
    else if(pid == 0){
        //子进程
        close(input[1]);//子进程从input读父进程传来的参数，所以不需要写，关闭写端
        close(output[0]);//子进程将执行结果写到管道发送给父进程，不需要读，所以
        //环境变量父子进程都能看到
        sprintf(method_env, "METHOD=%s", method);
        putenv(method_env);
        if(strcasecmp(method, "GET") == 0)
        {
            sprintf(quer_string_env, "QUERY_STRING=%s", cur_url);
            putenv(quer_string_env);
            printf("QUERY_STRING:%s\n", quer_string_env);
        }
        else
        {
            sprintf(content_length_env, "CONTENT_LENGTH=%d", content_length);
            putenv(content_length_env);
        }
        //将标准输入重定向到input
        //将标准输出重定向到output
        if(dup2(input[0], 0) < 0 || dup2(output[1], 1) < 0)
        {
            perror("dup2");
            return 404;
        }   
        int ret = execl(path, path, NULL);
        if(ret == -1)
        {
            perror("execl");
            return 404;
        }
        exit(-1);
    }
    else
    //父进程
    {
        close(input[0]);
        close(output[1]);
        int i = 0;
        char ch = '\0';
        if(strcasecmp(method, "post") == 0)
        {
            for(; i < content_length; i++)
            {
                read(sock, &ch,  1);
                write(input[1], &ch, 1);
            }
        }
        //走到GET
        ch = '\0';
        while(read(output[0], &ch, 1))
        {
            send(sock, &ch, 1, 0);
        }

        if(waitpid(pid, NULL, 0) < 0)
        {
            perror("waitpid");
            return 404;
        }
        close(sock);
        close(input[1]);
        close(output[0]);
    }
    return 200;
}

void echo_error(int sock, int status_code)
{
    (void)status_code;
    const char* _404_path = "webRoot/error_code/404/404.html";
    int fd = open(_404_path, O_RDONLY);
    if(fd < 0)
    {
        perror("open");
        return;
    }
    char buf[MAXSIZE/4];
    const char* Blank = "\r\n";
    sprintf(buf, "HTTP 404 Not Found\r\n");
    send(sock, buf, strlen(buf), 0);
    send(sock, Blank, strlen(Blank), 0);
    struct stat st;
    stat(_404_path, &st);
    ssize_t size = sendfile(sock, fd, NULL, st.st_size);
    close(fd);
}

void status_response(int sock, int status_code)
{
    clear_header(sock);
    switch(status_code)
    {
    case 404:
        echo_error(sock, status_code);
        break;
    case 503:
        break;
    default:
        break;
    }
}

int echo_www(int sock, const char* path, int size)
{
    clear_header(sock);
    int fd = open(path, O_RDONLY);
   if(fd < 0)
   {
       perror("open");
       return 404;
   }
    char line[MAXSIZE] = {0};
    //sprintf(len_buf, "Content-Length: %u\r\n", size);
    //send(sock, status_line, strlen(status_line), 0);
    //send(sock, len_buf, strlen(len_buf), 0);
    //send(sock, blank_line, strlen(blank_line), 0);
    sprintf(line, "HTTP/1.0 200 OK\r\n");
    send(sock, line, strlen(line), 0);
    sprintf(line, "Content-Type: text/html\r\n");
    send(sock, line, strlen(line), 0);
    sprintf(line, "\r\n");
    send(sock, line, strlen(line), 0);
    sendfile(sock, fd, NULL, size);
    close(fd);
    return 200;
}


static void* handle_client(void* arg){
    int sock = (int)arg;
    char buf[MAXSIZE] = {0}; 
    char method[MAXSIZE/32];
    char url[MAXSIZE];
    char path[MAXSIZE];
    int error_code = 200;
    size_t i = 0;
    size_t j = 0;
    char* cur_url;
    memset(buf,'\0',sizeof(buf));
    memset(method,'\0',sizeof(method));
    memset(url, '\0',sizeof(url));
    memset(path, '\0', sizeof(path));
    if(get_line(sock, buf, sizeof(buf)) < 0){
        error_code = 404;
        goto end;
    }
    //走到这里buf已经取到了第一行
    while(!isspace(buf[i]) && i < sizeof(buf) && j < sizeof(method) - 1){
        method[j++] = buf[i++];
    }
    method[j] = '\0';
    //已经获得了请求的方法
    j = 0;
    while(isspace(buf[i]) && i < sizeof(buf)){
        i++;
    }
    while(!isspace(buf[i]) && i < strlen(buf) && j < sizeof(url) - 1){
        url[j++] = buf[i++];
    }
    url[j] = '\0';
    printf("URL: %s\n",url);
    //已经获取到了URL
    //strcasecmp用忽略大小写比较字符串，通过strcasecmp函数可以指定每个
    //字符串用于比较的字符数，strncasecmp用来比较参数s1和s2字符串前n个字符，比较时会自动忽略大小写的差异。
    int cgi = 0;
    if(strcasecmp(method, "post") == 0){
        cgi = 1;
    }
    else if(strcasecmp(method, "get") == 0){
        cur_url = url;
        while(*cur_url != '\0'){
            if(*cur_url == '?'){
                *cur_url = '\0';
                cur_url++;
                cgi = 1;
                break;
            }
            cur_url++;
        }
    }
    else{
        error_code = 404;
        goto end;
    }   
    //将wwwRoot拼接到url之前，以命令形式输出到path
    sprintf(path,"webRoot%s",url);
    //请求的资源是web根目录，自动拼接上首页
    if(path[strlen(path)-1] == '/'){
        strcat(path,"index.html");
    }
    printf("path: %s\n", path);
    struct stat st;
   //stat() 通过文件名filename获取文件信息，并保存在buf所指的结构体stat中 
    if(stat(path, &st) < 0){
        error_code = 404;
        goto end;
    }else{
        if(S_ISDIR(st.st_mode)){
            //请求的资源如果是目录，给每个目录下加一个缺省的首页
            strcat(path, "index.html");
        }
        else{
            // S_IXUSR(S_IEXEC) 00100     文件所有者具可执行权限
            // S_IXGRP 00010             用户组具可执行权限
            // S_IXOTH 00001             其他用户具可执行权限
            if((st.st_mode & S_IXUSR) || (st.st_mode & S_IXGRP) || (st.st_mode & S_IXOTH)){
                cgi = 1;
            }
        }
        if(cgi == 1){
            error_code = exe_cgi(sock, path, method, cur_url);
        }
        else{
            // 不是cgi, get方法，不带参数
            //clear_header(sock);
            error_code = echo_www(sock, path, st.st_size);
        }
    }
end:
    if(error_code != 200)
    {
        status_response(sock, error_code);
    }
    close(sock);
}


int main(int argc, char* argv[]){

    if(argc != 2){
        usage();
        return 1;
    } 
    int port = atoi(argv[1]);
    int listen_socket = StartUp(port);
    signal(SIGPIPE, SIG_IGN);
    while(1){
        struct sockaddr_in client_addr;
        socklen_t len = sizeof(client_addr);
        int client_fd = accept(listen_socket, (struct sockaddr*)&client_addr, &len);
        if(client_fd < 0){
            printf("no client\n");
            continue;
        }
        //程序走到这里说明有新的连接到来
        printf("get a new connect\n");
        printf("client_id:%d\n", client_fd);
        pthread_t tid = 0;
        //利用子线程对接受到的套接字进行处理
        
        pthread_create(&tid, NULL, handle_client, (void*)client_fd);
        pthread_detach(tid);
    }
}
