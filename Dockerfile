FROM eclipse-temurin:17-jre
ARG JAR_FILE=build/libs/*.war
COPY ${JAR_FILE} app.war
ENTRYPOINT ["java", "-jar", "/app.war"]
