FROM java:8

VOLUME /tmp

ADD docker-demo-1.0-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar", "--spring.profiles.active=pro"]
