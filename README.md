# docker-demo
## Step 1
安装docker
#### MacOs
Homebrew 的 Cask 已经支持 Docker for Mac，因此可以很方便的使用 Homebrew Cask 来进行安装
 ```bash
 brew cask install docker
 ```
#### CentOs7
移除旧的版本
```bash
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
安装一些必要的系统工具
```bash
sudo yum install -y yum-utils device-mapper-persistent-data lvm2
```
添加软件源
```bash
sudo yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
```
更新 yum 缓存
```bash
sudo yum makecache fast
```
安装 Docker-ce
```bash
sudo yum -y install docker-ce
```
启动 Docker 后台服务
```bash
sudo systemctl start docker
```
测试运行 hello-world
```bash
docker run hello-world
```

## Step 2
使用Maven对项目进行编译  
```bash
mvn clean install docker:build
```

## Step 3
使用docker直接运行项目  
```    bash
docker run -it -p 8080:9080 guoxin/docker-demo
```

## Step 4
安装Jenkins并使用自动化部署该项目
### 使用Docker部署并运行jenkins
使用docker同步jenkins镜像
```bash
docker pull jenkins/jenkins:lts
```
>lts: Pulling from jenkins/jenkins  
>bc9ab73e5b14: Pull complete  
>193a6306c92a: Pull complete  
>e5c3f8c317dc: Pull complete  
>a587a86c9dcb: Pull complete  
>a4c7ee7ef122: Pull complete  
>a7c0dad691e9: Pull complete  
>367a6a68b113: Pull complete  
>60c0e52d1ec2: Pull complete  
>c9d22bc43935: Pull complete  
>15c4a0eee347: Pull complete  
>1a9781850ac9: Pull complete  
>1ea8f3e44193: Pull complete  
>5ca425533c1f: Pull complete  
>788cd92af158: Pull complete  
>16312cbc06e7: Pull complete  
>9795c04ecdb6: Pull complete  
>117efa16c088: Pull complete  
>fee3f41fc6be: Pull complete  
>85eb7ce5dfc8: Pull complete  
>f6363532d084: Pull complete  
>f7cf9fe897f9: Pull complete  
>b3e19529d0eb: Pull complete  
>Digest: sha256:a1c7f439da639c75135a667cf0bf164b0db546e8b790c2fce5aed77aba7bc0f1  
>Status: Downloaded newer image for jenkins/jenkins:lts  

运行jenkins的镜像的时候有两种映射本地磁盘的方式
#### 第一种 挂载指定目录

创建jenkins目录  
```    bash
mkdir ~/jenkins_home
```
给目录的归属者赋给UID1000，这里有一个权限的问题
[详情请看这里](https://blog.csdn.net/mmd0308/article/details/77206563)
```    bash
chown -R 1000:1000 ~/jenkins_home
```
```    bash
docker run -d -v ~/jenkins_home:/var/jenkins_home -p 8080:8080 -p 50000:50000 jenkins/jenkins:lts
```

```bash
cat ~/jenkins_home/_data/secrets/initialAdminPassword
```
>e9ce325859834111943e766fe07c9e55
#### 第二种 挂载Volume (推荐)
```bash
docker run -d -v jenkins_home:/var/jenkins_home -p 8080:8080 -p 50000:50000 jenkins/jenkins:lts
```
```bash
docker inspect jenkins_home
```
>[  
>    {  
>        "CreatedAt": "2018-11-17T16:50:02+08:00",  
>        "Driver": "local",  
>        "Labels": null,  
>        "Mountpoint": "/var/lib/docker/volumes/jenkins_home/_data",  
>        "Name": "jenkins_home",  
>        "Options": null,  
>        "Scope": "local"  
>    }  
>]  

我们可以通过Mountpoint属性的路径进入到Volume所映射的路径，可以查看相关文件（但是不推荐在宿主机中直接修改文件推荐直接进入到容器中进行修改操作）
```bash
cat /var/lib/docker/volumes/jenkins_home/_data/secrets/initialAdminPassword
```
>e9ce325859834111943e766fe07c9e55

这两种方法都可以实现把容器里的数据持久化到本地，但是更推荐第二种方法  

### Jenkins登陆白屏
```xml
<authorizationStrategy class="hudson.security.AuthorizationStrategy$Unsecured"/>
  <securityRealm class="hudson.security.SecurityRealm$None"/>
```
将密码填写到jenkins的页面中就可以了
