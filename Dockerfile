FROM openjdk:17
ADD target/terrasense.jar terrasense.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/terrasense.jar"]