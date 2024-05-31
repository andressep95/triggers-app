# Etapa 1: Construir la aplicación con Maven
FROM maven:3.8.4-openjdk-11 AS build

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo pom.xml y resolver las dependencias
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiar el resto del código fuente
COPY src ./src

# Compilar la aplicación
RUN mvn package -DskipTests

# Etapa 2: Desplegar en Tomcat
FROM tomcat:9.0

# Copiar el archivo WAR desde la etapa de construcción
COPY --from=build /app/target/ROOT.war /usr/local/tomcat/webapps/ROOT.war

# Exponer el puerto 8080
EXPOSE 8080

# Ejecutar Tomcat
CMD ["catalina.sh", "run"]
