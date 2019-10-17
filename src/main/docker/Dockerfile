FROM frolvlad/alpine-oraclejdk8:slim
maintainer sxx
add spring-boot-weather.0.0.1-SNAPSHOT /mall-tiny-docker-file.jar
RUN bash -c 'touch /mall-tiny-docker-file.jar'
EXPOSE 8080
CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/ma
ll-tiny-docker-file.jar"]