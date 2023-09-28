
# Estágio 1: Construção do Pacote
FROM maven:3-openjdk-17 AS build-image

# Crie um diretório de trabalho chamado /to-build-app
WORKDIR /to-build-app

# Copie os arquivos necessários para o contêiner
COPY . .

# Instale as dependências do Maven e crie o pacote JAR
RUN mvn dependency:go-offline
RUN mvn package -DskipTests

# Estágio 2: Imagem Final
FROM eclipse-temurin:17-jre-alpine

# Crie um diretório de trabalho chamado /app
WORKDIR /app

# Copie o pacote JAR da etapa de construção para o contêiner final
COPY --from=build-image /to-build-app/target/*.jar /app/app.jar

# Exponha a porta 8080 para a aplicação
EXPOSE 8080

# Defina o ponto de entrada para executar a aplicação
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
