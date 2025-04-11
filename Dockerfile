# Dockerfile
FROM openjdk:17
COPY . /app
WORKDIR /app
CMD ["java", "-jar", "yourapp.jar"]
