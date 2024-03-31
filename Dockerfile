FROM ubuntu:latest AS build

# Atualize os pacotes do sistema e instale as dependências necessárias
RUN apt-get update && \
    DEBIAN_FRONTEND=noninteractive apt-get install -y wget && \
    wget https://download.java.net/java/GA/jdk21.0.1/9363828f724542ef9ea295c57e7e6e1f/9/GPL/openjdk-21.0.1_linux-x64_bin.tar.gz && \
    tar xvf openjdk-21.0.1_linux-x64_bin.tar.gz && \
    mkdir -p /usr/lib/jvm && \
    mv jdk-21.0.1 /usr/lib/jvm/java-21-openjdk && \
    rm openjdk-21.0.1_linux-x64_bin.tar.gz && \
    apt-get remove -y wget && \
    apt-get clean && \
    update-alternatives --install /usr/bin/java java /usr/lib/jvm/java-21-openjdk/bin/java 1 && \
    update-alternatives --set java /usr/lib/jvm/java-21-openjdk/bin/java && \
    update-alternatives --install /usr/bin/javac javac /usr/lib/jvm/java-21-openjdk/bin/javac 1 && \
    update-alternatives --set javac /usr/lib/jvm/java-21-openjdk/bin/javac

# Defina a variável de ambiente JAVA_HOME
ENV JAVA_HOME /usr/lib/jvm/java-21-openjdk

# Defina o diretório de trabalho
WORKDIR /app

# Copie o seu arquivo JAR ou o código fonte para o diretório de trabalho no contêiner
COPY . /app

RUN apt-get install maven -y
RUN mvn clean install

FROM openjdk:21-jdk-slim
EXPOSE 8080

COPY --from=build /target/app.jar app.jar

ENTRYPOINT [ "java", "-jar", "app.jar"]

