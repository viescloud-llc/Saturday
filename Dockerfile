FROM openjdk:19
EXPOSE 8080
EXPOSE 81
EXPOSE 82
EXPOSE 83
ADD target/saturday.jar saturday.jar
ENTRYPOINT ["java", "-jar", "/saturday.jar"]