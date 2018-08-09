#include "tcp.h"

void Usage()
{
    printf("Usage:./server port\n");
}

//http发来的报文：app id close/open
//mcu发来的报文： mcu id connect

int getline(int client_fd, char* buf, char* source, char* id,  char* directive)
{
    ssize_t read_size = read(client_fd, buf, MAXSIZE);
    if(read_size == 0)
    {
        close(client_fd);
        return -1;
    }
    size_t i = 0;
    size_t j = 0;
    while(!isspace(buf[i]))
    {
        source[j++] = buf[i++];
    }
    source[j] = '\0';
    //printf("%s\n", source);
    //走到这儿，将客户端确定
    while(isspace(buf[i]))
    {
        i++;
    }
    j = 0;
    while(!isspace(buf[i]))
    {
        directive[j++] = buf[i++];
    }
    directive[j] = '\0';
    //走到这儿读到命令
    while(isspace(buf[i]))
    {
        i++;
    }
    j = 0;
    while(buf[i] != '\0')
    {
        id[j++] = buf[i++];
    }
    id[j] = '\0';
    //读到id
    return 1;
}

int findSockId(int id, std::vector<sockid> mq)
{
    size_t i = 0;
    for(; i < mq.size(); i++)
    {
        if(mq[i].id == id)
        {
            break;
        }
    }
    if(i >= mq.size())
    {
        return -1;
    }
    return mq[i].client_fd;
}

#if 0
void printMq(std::vector<sockid> mq)
{
    int i = 0;
    if(mq.size() == 0)
    {
        printf("no mcu_client\n");
        return;
    }
    for(; i < mq.size(); i++)
    {
        printf("mcufd:%d mcuid:%d\n", mq[i].client_fd, mq[i].id);
    }
    return;
}
#endif

void ProcessRequest(int client_fd, std::vector<sockid> mq){
    //tcp ok
    char source[MAXSIZE/4] = {0};
    char directive[MAXSIZE/4] = {0};
    char buf[MAXSIZE] = {0};
    char id[MAXSIZE/4] = {0};
    getline(client_fd, buf, source, id,  directive);
   // printf("source:%s\n", source);
   // printf("directive:%s\n", directive);
   // printf("id:%s\n", id);
    if(strcasecmp(source, "mcu") == 0)
    {
        //如果是mcu，
        //1.需要将它维护起来
        //2.发送一个响应让其亮一下
        sockid sd;
        sd.client_fd = client_fd;
        sd.id = atoi(id);
        mq.push_back(sd);
        char respond_mcu[] = "tcp ok";
        send(client_fd, respond_mcu, sizeof(respond_mcu), 0);
    }
    else if(strcasecmp(source, "app") == 0)
    {
        //如果是app,
        //1.需要响应一个接收成功报文
        //2.需要根据id找到mcu的fd
        char respond[] = "tcp ok";
        send(client_fd, respond, sizeof(respond), 0);
        int mcu_fd = findSockId(atoi(id), mq);
        send(mcu_fd, directive, sizeof(directive), 0);
    }
   // printMq(mq);
}


void* CreateWorker(void* ptr)
{
    Arg* arg = (Arg*)ptr;
    ProcessRequest(arg->fd, arg->Mq);
    free(arg);
    return NULL;
}

int main(int argc,char* argv[])
{
    if(argc != 2)
    {
        Usage();
        return -1;
    }
    int opt = 1;
    std::vector<sockid> mq;
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
    setsockopt(fd, SOL_SOCKET, SO_REUSEADDR, &opt, sizeof(opt));
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
        arg->Mq = mq;
        pthread_create(&tid, NULL, CreateWorker, (void*)arg);
        pthread_detach(tid);
    }
    return 0;
}
