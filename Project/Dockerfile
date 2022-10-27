FROM gradle:7.2.0-jdk17 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build



FROM eclipse-temurin:17-jre-alpine
EXPOSE 8080
VOLUME /springLogOut
COPY --from=BUILD /home/gradle/src/build/libs/*SNAPSHOT.jar /app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]

# sudo docker build -t dianamd21/crud-tarea-1 .
# sudo docker run -p 80:8080 -d dianamd21/crud-tarea-1