# Imagen base con Java 17 (puedes ajustar la versión)
FROM eclipse-temurin:17-jdk-alpine

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el JAR generado por Maven
COPY target/*.jar app.jar

# Comando de arranque
CMD ["java", "-jar", "app.jar"]
