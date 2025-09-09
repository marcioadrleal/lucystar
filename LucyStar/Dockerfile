# ============================
# Stage 1: Build da aplicação
# ============================
FROM maven:3.9.6-eclipse-temurin-21 AS build

# Define o diretório de trabalho
WORKDIR /app

# Copia o arquivo pom.xml e baixa as dependências antes de copiar o código fonte
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copia o restante do projeto e faz o build
COPY src ./src
RUN mvn clean package -DskipTests

# ============================
# Stage 2: Imagem final leve
# ============================
FROM eclipse-temurin:21-jdk-alpine

# Define o diretório de trabalho
WORKDIR /app

# Copia o JAR gerado no estágio anterior
COPY --from=build /app/target/*.jar app.jar

# Expõe a porta da aplicação
EXPOSE 9001

# Comando para rodar o Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]