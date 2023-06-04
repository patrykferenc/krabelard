FROM eclipse-temurin:17 AS build
WORKDIR /
ADD . .
RUN mkdir /b/
RUN ./gradlew clean bootJar
RUN mv build/libs/*.jar /b/

FROM eclipse-temurin:17

RUN mkdir /opt/app
COPY --from=build /b/lines.jar /opt/app

WORKDIR /opt/app

EXPOSE 8080

CMD ["java", "-jar", "lines.jar"]