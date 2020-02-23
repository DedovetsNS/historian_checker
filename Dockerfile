FROM openjdk:11
ADD build/libs/historian_checker.jar historian_checker.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","historian_checker.jar"]

