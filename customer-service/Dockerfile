FROM openjdk:8
EXPOSE 9090
ADD target/customer-service.jar customer-service.jar
ENTRYPOINT [ "java","-jar","/customer-service.jar" ]