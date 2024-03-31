FROM ubuntu:20.04

RUN apt-get update && \
    apt-get upgrade -y && \
    apt-get install -y wget

# Download e instalação do OpenJDK 20
RUN wget -qO - https://adoptopenjdk.jfrog.io/adoptopenjdk/api/gpg/key/public | apt-key add - && \
    echo "deb https://adoptopenjdk.jfrog.io/adoptopenjdk/deb focal main" | tee /etc/apt/sources.list.d/adoptopenjdk.list && \
    apt-get update && \
    apt-get install -y adoptopenjdk-20-hotspot

# Instalação do Maven
RUN apt-get install -y maven

# Definir a variável de ambiente JAVA_HOME
ENV JAVA_HOME /usr/lib/jvm/adoptopenjdk-20-hotspot-amd64

RUN mvn clean install

# Criar um diretório de trabalho
WORKDIR /app

# Copiar o arquivo JAR da sua aplicação para o contêiner
COPY ./target/app.jar /app/app.jar

# Comando para executar a aplicação Java
CMD ["java", "-jar", "app.jar"]