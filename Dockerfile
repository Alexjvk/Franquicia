# Usar una imagen base para construir la aplicación
FROM openjdk:17-jdk-slim AS build

# Configurar el directorio de trabajo
WORKDIR /app

COPY pom.xml ./
COPY mvnw ./
COPY mvnw.cmd ./
COPY .mvn ./.mvn
COPY src ./src

RUN chmod +x mvnw

# Construir la aplicación
RUN ./mvnw clean package -DskipTests

EXPOSE 8080

CMD ["java", "-jar", "target/demo-0.0.1-SNAPSHOT.jar"]


