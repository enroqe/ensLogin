FROM adoptopenjdk/openjdk8
EXPOSE 80
COPY target/ensLogin-0.0.2.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]