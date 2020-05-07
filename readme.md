# 后台API

##### 运行

```cmd
/**
*internetAddress	填写本机的外网IP
*server.port		程序监听的端口
*/
java -jar phq-0.0.1-SNAPSHOT.jar --internetAddress=192.168.1.1 --server.port=80
```



###### 环境

程序在需要时会在同目录下生成static目录，可以自己创建，static目录下的文件可以直接访问。