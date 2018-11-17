# docker-demo
## Step 1
安装docker
#### MacOs
>Homebrew 的 Cask 已经支持 Docker for Mac，因此可以很方便的使用 Homebrew Cask 来进行安装
 ```bash
 brew cask install docker
 ```
#### CentOs7
>移除旧的版本
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
>安装一些必要的系统工具
```bash
sudo yum install -y yum-utils device-mapper-persistent-data lvm2
```
>添加软件源
```bash
sudo yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
```
>更新 yum 缓存
```bash
sudo yum makecache fast
```
>安装 Docker-ce
```bash
sudo yum -y install docker-ce
```
>启动 Docker 后台服务
```bash
sudo systemctl start docker
```
>测试运行 hello-world
```bash
docker run hello-world
```

## Step 2
>使用Maven对项目进行编译  
`mvn clean install docker:build`

## Step 3
>使用docker直接运行项目  
`docker run -it -p 8080:9080 guoxin/docker-demo`

## Step 4
>安装Jenkins并使用自动化部署该项目
#### 使用Docker部署并运行jenkins
>使用docker同步jenkins镜像
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

> 创建jenkins目录  
`mkdir ~/jenkins_home`
>给目录的归属者赋给UID1000，这里有一个权限的问题
[详情请看这里](https://blog.csdn.net/mmd0308/article/details/77206563)
`chown -R 1000:1000 ~/jenkins_home`
`docker run -d -v ~/jenkins_home:/var/jenkins_home -p 8080:8080 -p 50000:50000 jenkins/jenkins:lts`

>9f7f87d7bb36a2b94f76d154ef13b65332bd470b482134438b24313c119945c1

`docker logs 9f7f87d7bb36a2b94f76d154ef13b65332bd470b482134438b24313c119945c1`
>Running from: /usr/share/jenkins/jenkins.war
>webroot: EnvVars.masterEnvVars.get("JENKINS_HOME")
>Nov 17, 2018 8:10:32 AM org.eclipse.jetty.util.log.Log initialized
>INFO: Logging initialized @426ms to org.eclipse.jetty.util.log.JavaUtilLog
>Nov 17, 2018 8:10:32 AM winstone.Logger logInternal
>INFO: Beginning extraction from war file
>Nov 17, 2018 8:10:33 AM org.eclipse.jetty.server.handler.ContextHandler setContextPath
>WARNING: Empty contextPath
>Nov 17, 2018 8:10:33 AM org.eclipse.jetty.server.Server doStart
>INFO: jetty-9.4.z-SNAPSHOT; built: 2018-06-05T18:24:03.829Z; git: d5fc0523cfa96bfebfbda19606cad384d772f04c; jvm 1.8.0_181-8u181-b13-2~deb9u1-b13
>Nov 17, 2018 8:10:33 AM org.eclipse.jetty.webapp.StandardDescriptorProcessor visitServlet
>INFO: NO JSP Support for /, did not find org.eclipse.jetty.jsp.JettyJspServlet
>Nov 17, 2018 8:10:33 AM org.eclipse.jetty.server.session.DefaultSessionIdManager doStart
>INFO: DefaultSessionIdManager workerName=node0
>Nov 17, 2018 8:10:33 AM org.eclipse.jetty.server.session.DefaultSessionIdManager doStart
>INFO: No SessionScavenger set, using defaults
>Nov 17, 2018 8:10:33 AM org.eclipse.jetty.server.session.HouseKeeper startScavenging
>INFO: node0 Scavenging every 660000ms
>Jenkins home directory: /var/jenkins_home found at: EnvVars.masterEnvVars.get("JENKINS_HOME")
>Nov 17, 2018 8:10:34 AM org.eclipse.jetty.server.handler.ContextHandler doStart
>INFO: Started w.@67ef029{Jenkins v2.138.3,/,file:///var/jenkins_home/war/,AVAILABLE}{/var/jenkins_home/war}
>Nov 17, 2018 8:10:34 AM org.eclipse.jetty.server.AbstractConnector doStart
>INFO: Started ServerConnector@5aabbb29{HTTP/1.1,[http/1.1]}{0.0.0.0:8080}
>Nov 17, 2018 8:10:34 AM org.eclipse.jetty.server.Server doStart
>INFO: Started @2802ms
>Nov 17, 2018 8:10:34 AM winstone.Logger logInternal
>INFO: Winstone Servlet Engine v4.0 running: controlPort=disabled
>Nov 17, 2018 8:10:35 AM jenkins.InitReactorRunner$1 onAttained
>INFO: Started initialization
>Nov 17, 2018 8:10:35 AM jenkins.InitReactorRunner$1 onAttained
>INFO: Listed all plugins
>Nov 17, 2018 8:10:36 AM jenkins.InitReactorRunner$1 onAttained
>INFO: Prepared all plugins
>Nov 17, 2018 8:10:36 AM jenkins.InitReactorRunner$1 onAttained
>INFO: Started all plugins
>Nov 17, 2018 8:10:36 AM jenkins.InitReactorRunner$1 onAttained
>INFO: Augmented all extensions
>Nov 17, 2018 8:10:37 AM jenkins.InitReactorRunner$1 onAttained
>INFO: Loaded all jobs
>Nov 17, 2018 8:10:37 AM hudson.model.AsyncPeriodicWork$1 run
>INFO: Started Download metadata
>Nov 17, 2018 8:10:37 AM jenkins.util.groovy.GroovyHookScript execute
>INFO: Executing /var/jenkins_home/init.groovy.d/tcp-slave-agent-port.groovy
>Nov 17, 2018 8:10:38 AM org.springframework.context.support.AbstractApplicationContext prepareRefresh
>INFO: Refreshing org.springframework.web.context.support.StaticWebApplicationContext@4b30a440: display name [Root WebApplicationContext]; startup date [Sat Nov 17 08:10:38 UTC 2018]; root of context hierarchy
>Nov 17, 2018 8:10:38 AM org.springframework.context.support.AbstractApplicationContext obtainFreshBeanFactory
>INFO: Bean factory for application context [org.springframework.web.context.support.StaticWebApplicationContext@4b30a440]: org.springframework.beans.factory.support.DefaultListableBeanFactory@5bc15702
>Nov 17, 2018 8:10:38 AM org.springframework.beans.factory.support.DefaultListableBeanFactory preInstantiateSingletons
>INFO: Pre-instantiating singletons in org.springframework.beans.factory.support.DefaultListableBeanFactory@5bc15702: defining beans [authenticationManager]; root of factory hierarchy
>Nov 17, 2018 8:10:38 AM org.springframework.context.support.AbstractApplicationContext prepareRefresh
>INFO: Refreshing org.springframework.web.context.support.StaticWebApplicationContext@63342122: display name [Root WebApplicationContext]; startup date [Sat Nov 17 08:10:38 UTC 2018]; root of context hierarchy
>Nov 17, 2018 8:10:38 AM org.springframework.context.support.AbstractApplicationContext obtainFreshBeanFactory
>INFO: Bean factory for application context [org.springframework.web.context.support.StaticWebApplicationContext@63342122]: org.springframework.beans.factory.support.DefaultListableBeanFactory@7446711b
>Nov 17, 2018 8:10:38 AM org.springframework.beans.factory.support.DefaultListableBeanFactory preInstantiateSingletons
>INFO: Pre-instantiating singletons in org.springframework.beans.factory.support.DefaultListableBeanFactory@7446711b: defining beans [filter,legacy]; root of factory hierarchy
>Nov 17, 2018 8:10:38 AM jenkins.install.SetupWizard init
>INFO:
>
>*************************************************************
>*************************************************************
>*************************************************************
>
>Jenkins initial setup is required. An admin user has been created and a password generated.
>Please use the following password to proceed to installation:
>
>b5c3fccee7b14caeac1e3917890c6dd4
>
>This may also be found at: /var/jenkins_home/secrets/initialAdminPassword
>
>*************************************************************
>*************************************************************
>*************************************************************

将日志中的密码填写到jenkins的页面中就可以了