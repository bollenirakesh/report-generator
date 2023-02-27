FROM amazoncorretto:11
COPY target/report-generator.jar /app.jar
RUN mkdir "/app-data"
ENTRYPOINT ["java","-jar","/app.jar"]