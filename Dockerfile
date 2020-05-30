FROM adoptopenjdk/openjdk14
FROM maven

WORKDIR /opt/app

COPY pom.xml .
COPY src src/

RUN mvn clean package

EXPOSE 9080

ENTRYPOINT ["java","-jar","target/visitor-application-0.0.1-SNAPSHOT.jar"]