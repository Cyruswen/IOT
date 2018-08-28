#include "tcp.h"

std::vector<sockid> Mq;
std::mutex g_io_mutex;

void Usage()
{
    printf("Usage:./server port\n");
}

//http发来的报文：app id close/open
//mcu发来的报文： mcu id connect

int getline(int client_fd, char* buf, char* source, char* id, char* directive)
{
    size_t n = 0;
    size_t count = 0;
    int read_size = read(client_fd, buf, 1024);
    printf("read_size:%d\n", read_size);
    printf("request from cgi:%s\n", buf);
    for(; n < read_size; n++)
    {
        if(isspace(buf[n]))
        {
            count++;
        }
    }
    if(count != 2)
    {
        char res[] = "format errors";
        write(client_fd, res, 14);
        close(client_fd);
        return -1;
    }
    if(read_size == 0)
    {
        close(client_fd);
        return -1;
    }
    size_t i = 0;
    size_t j = 0;
    while(!isspace(buf[i]) && i < read_size)
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
    while(!isspace(buf[i]) && i < read_size)
    {
        id[j++] = buf[i++];
    }
    id[j] = '\0';
    //读到id
    while(isspace(buf[i]))
    {
        i++;
    }
    j = 0;
    while(buf[i] != '\0')
    {
        directive[j++] = buf[i++];
    }
    directive[j] = '\0';
    //走到这儿读到命令
    return 1;
}

int findSockId(int id)
{
    size_t i = 0;
    for(; i < Mq.size(); i++)
    {
        if(Mq[i].id == id)
        {
            break;
        }
    }
    if(i >= Mq.size())
    {
        return -1;
    }
    return Mq[i].client_fd;
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


//void setStatus(int id, char* mcu_status)
//{
//    size_t i = 0;
//    for(; i < Mq.size(); i++)
//    {
//        if(Mq[i].id == id)
//        {
//            strcpy(Mq[i].mcu_status, mcu_status);
//            break;
//        }
//    }
//    return;
//}

static void* CreateWorker(void* ptr)
{
    size_t client_fd = (size_t)ptr;
    printf("new client_fd: %lu\n", client_fd);
    char source[MAXSIZE/4] = {0};
    char directive[MAXSIZE/4] = {0};
    char buf[MAXSIZE] = {0};
    char id[MAXSIZE/4] = {0};
    int ret = getline(client_fd, buf, source, id,  directive);
    if(ret == -1)
    {
        printf("报文格式错误，已断开连接\n");
        return NULL;
    }
    printf("source:%s\n", source);
    printf("directive:%s\n", directive);
    printf("id:%s\n", id);
    if(strcasecmp(source, "mcu") == 0)
    {
        //如果是mcu，
        //1.需要将它维护起来
        //维护的内容有该nodemcu的client_fd
        g_io_mutex.lock();
        sockid sd;
        sd.client_fd = client_fd;
        sd.id = atoi(id);
        Mq.push_back(sd);
        g_io_mutex.unlock();
    }
    else if(strcasecmp(source, "app") == 0)
    {
        //如果是app,如果mcu没有连接，则返回错误
        //1.需要响应一个接收成功报文
        //2.需要根据id找到mcu的fd
        //char buf[1024] = {0};
        g_io_mutex.lock();
        int mcu_fd = findSockId(atoi(id));
        g_io_mutex.unlock();
        if(mcu_fd == -1)
        {
            printf("there is no such id\n");
            return NULL;
        }
        send(mcu_fd, directive, sizeof(directive), 0);
        //read(mcu_fd, buf, sizeof(buf));
        //printf("buf:%s\n", buf);
        //更新维护表中mcu的状态
        //getline(mcu_fd, buf, source, id,  directive);
        //setStatus(atoi(id), directive);
        close(client_fd); 
    }
    else
    {
        char res[] = "source errors";
        write(client_fd, res, 14);
        close(client_fd);
    }
   // printMq(mq);
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
#if 0
    sockid s;
    s.id = 1001;
    s.client_fd = 2;
    Mq.push_back(s);
#endif

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
        size_t client_fd = accept(fd, (struct sockaddr*)&client_fd, &len);
        printf("get a connect.\n");
        printf("client_fd: %lu\n", client_fd);
        if(client_fd < 0)
        {
            perror("accept");
            continue;
        }
        //char buf[1024] = {0};
        //int ret = read(client_fd, buf, 1024);
        //printf("%d\n", ret);
        //printf("%s\n", buf);
        pthread_t tid;
        pthread_create(&tid, NULL, CreateWorker, (void*)client_fd);
        pthread_detach(tid);
    }
    return 0;
}
