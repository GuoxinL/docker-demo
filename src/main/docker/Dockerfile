FROM java:8

VOLUME /tmp

ADD docker-demo-1.0-SNAPSHOT.jar application.jar

ENTRYPOINT ["java","-jar","/application.jar", "--spring.profiles.active=pro"]
