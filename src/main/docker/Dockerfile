FROM java:8

VOLUME /tmp

#设置时区
RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone

ADD docker-demo-1.0-SNAPSHOT.jar application.jar

ENTRYPOINT ["java","-jar","/application.jar", "--spring.profiles.active=pro"]
