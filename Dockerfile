FROM openjdk:11
VOLUME /tmp
EXPOSE 8080
RUN mkdir -p /opt/app
COPY target/*.jar /opt/app/japp.jar
ENTRYPOINT ["java", "-jar", "/opt/app/japp.jar"]