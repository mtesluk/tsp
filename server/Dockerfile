FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/*.jar tsp.jar
ENTRYPOINT ["java", "-Xms256m -Xmx256m -Xss512k -jar -Dspring.profiles.active=prod", "tsp.jar"]
