FROM openjdk:11
ARG JAR_FILE=target/*.jar
ADD target/EasyPatient-0.0.1-SNAPSHOT.jar EasyPatient-0.0.1-SNAPSHOT.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "EasyPatient-0.0.1-SNAPSHOT.jar"]