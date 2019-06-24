FROM adoptopenjdk/openjdk8
COPY target/ensLogin-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 80
ENTRYPOINT ["java","-jar","/app.jar"]