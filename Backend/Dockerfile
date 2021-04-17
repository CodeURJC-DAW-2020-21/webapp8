#FROM maven as builder
#COPY . /code/
#WORKDIR /code
#RUN mvn package
#FROM openjdk:11-jre
#COPY --from=builder /code/target/*.jar /usr/app/
#WORKDIR /usr/app/
#CMD [ "java", "-jar", "java-dawebapp8-0.0.1.jar" ]

FROM openjdk:11-jre
ADD target/*.jar webapp8.jar
EXPOSE 8443
ENTRYPOINT ["java","-jar",""]