
# Stage 1: Build WAR
FROM maven:3.9-eclipse-temurin-17 AS build

WORKDIR /app

COPY pom.xml .

RUN mvn -B dependency:go-offline

COPY src ./src

RUN mvn -B -DskipTests package


# Stage 2: Run WAR in Tomcat
FROM tomcat:11.0-jre17-temurin

# Deploy WAR as ROOT application
COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/ROOT.war

# Tomcat internal port
EXPOSE 8080

# Start Tomcat in foreground
CMD ["catalina.sh", "run"]
