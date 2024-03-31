FROM adoptopenjdk/openjdk16:alpine-jre

# Definindo variáveis de ambiente
ENV APP_NAME="app.jar" \
    APP_VERSION="1.0" \
    APP_PORT="8080"

# Definindo o diretório de trabalho dentro da imagem
WORKDIR /app

RUN mvn clean install

# Copiando o arquivo JAR da sua aplicação para o diretório de trabalho na imagem
COPY ./app.jar /app/app.jar

# Expondo a porta que a aplicação irá utilizar
EXPOSE $APP_PORT

# Comando para executar a aplicação quando o container for iniciado
CMD ["java", "-jar", "app.jar"]
