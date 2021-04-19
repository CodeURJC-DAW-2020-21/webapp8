FROM openjdk:11-jre
#ADD target/*.jar app.jar
#EXPOSE 8443
#ENTRYPOINT ["java","-jar","app.jar"]

COPY ./*.jar /usr/app/
WORKDIR /usr/app
CMD [ "java", "-jar", "juding-1.0.jar" ]
