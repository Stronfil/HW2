FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM tomcat:8.0-jre8
COPY --from=build /home/app/target/lesson1.war /usr/local/tomcat/webapps/lesson1.war
EXPOSE 8080
CMD ["catalina.sh", "run"]