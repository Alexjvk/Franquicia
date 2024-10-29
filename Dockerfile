# Usar una imagen base para construir la aplicación
FROM openjdk:17-jdk-slim AS build

# Configurar el directorio de trabajo
WORKDIR /app

# Copiar el archivo pom.xml, mvnw y el código fuente
COPY pom.xml ./
COPY mvnw ./
COPY mvnw.cmd ./
COPY .mvn ./.mvn
COPY src ./src

# Hacer el script mvnw ejecutable
RUN chmod +x mvnw

# Construir la aplicación
RUN ./mvnw clean package -DskipTests

# Exponer el puerto 8080
EXPOSE 8080

# Ejecutar la aplicación
CMD ["java", "-jar", "target/demo-0.0.1-SNAPSHOT.jar"]


