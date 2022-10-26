FROM gradle:7.2.0-jdk17 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle bootJar --no-daemon



FROM eclipse-temurin:17-jre-jammy
VOLUME /outputs
ENV PORT=8080
EXPOSE 8080
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/mi-app-multi-stage.jar
ENTRYPOINT ["java","-jar","/app/mi-app-multi-stage.jar"]

# sudo docker build -t crud-simple .
# sudo docker run -p 8080:8080 -d crud-simple