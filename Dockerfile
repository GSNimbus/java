# Etapa 1: build (com cache eficiente)
FROM bellsoft/liberica-openjdk-alpine:21 AS builder
ENV JAR_FILE=api-0.0.1-SNAPSHOT.jar
WORKDIR /app

# Copia o Maven Wrapper e outros arquivos necessários
COPY mvnw* /app/
COPY .mvn /app/.mvn
COPY pom.xml /app
COPY ./src /app/src

# Torna o Maven Wrapper executável
RUN chmod +x ./mvnw

# Executa o build do projeto
RUN ./mvnw dependency:go-offline -B package -DskipTests

# Etapa 2: imagem final mínima (só com o .jar)
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar

ENV CHAVE_API=""

EXPOSE 8080

CMD ["sh", "-c", "if [ -z \"$CHAVE_API\" ]; then echo 'A chave da api é obrigatoria como variavel de ambiente!' >&2; exit 1; fi && java -jar app.jar"]