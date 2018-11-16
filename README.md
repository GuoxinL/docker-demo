# docker-demo
## Step 1
安装docker
#### MacOs
>Homebrew 的 Cask 已经支持 Docker for Mac，因此可以很方便的使用 Homebrew Cask 来进行安装
 ```$xslt
 brew cask install docker
 ```
#### CentOs7
>移除旧的版本
```$xslt
sudo yum remove docker \
                  docker-client \
                  docker-client-latest \
                  docker-common \
                  docker-latest \
                  docker-latest-logrotate \
                  docker-logrotate \
                  docker-selinux \
                  docker-engine-selinux \
                  docker-engine
```
>安装一些必要的系统工具
```$xslt
sudo yum install -y yum-utils device-mapper-persistent-data lvm2
```
>添加软件源
```$xslt
sudo yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
```
>更新 yum 缓存
```$xslt
sudo yum makecache fast
```
>安装 Docker-ce
```$xslt
sudo yum -y install docker-ce
```
>启动 Docker 后台服务
```$xslt
sudo systemctl start docker
```
>测试运行 hello-world
```$xslt
docker run hello-world
```

## Step 2
>使用Maven对项目进行编译  
`mvn clean install docker:build`

## Step 3
>使用docker直接运行项目  
`docker run -it -p 8080:9080 guoxin/docker-demo`