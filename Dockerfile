# Etapa 1: Build con Maven
FROM eclipse-temurin:17-jdk-alpine AS build
WORKDIR /app

# 1. Copiamos solo los archivos de configuración de Maven
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# 2. Descargamos las dependencias (esta capa se cachea si el pom.xml no cambia)
RUN chmod +x mvnw
# Este comando descarga todo lo necesario sin compilar el código aún
RUN ./mvnw dependency:go-offline -B

# 3. Ahora sí copiamos el código y compilamos
# Como las dependencias ya están en el sistema, esto será mucho más rápido
COPY src ./src
RUN ./mvnw clean package -DskipTests

# Etapa 2: Imagen final ligera
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
# Copiamos el .jar desde la etapa de build
COPY --from=build /app/target/*.jar app.jar

# Tip de QA: Usar parámetros de optimización de memoria para contenedores
CMD ["java", "-XX:+UseContainerSupport", "-Xmx512m", "-jar", "app.jar"]