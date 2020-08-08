FROM java:8
MAINTAINER Deepu
EXPOSE 8089
ADD target/library-api.jar library-api.jar
ENTRYPOINT ["java", "-jar", "/library-api.jar"]