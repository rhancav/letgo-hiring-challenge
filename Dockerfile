FROM openjdk:21

EXPOSE 8080:8080

ADD target/*.jar letgo-hiring-challenge.jar

ENTRYPOINT ["java", "-jar", "/letgo-hiring-challenge.jar"]