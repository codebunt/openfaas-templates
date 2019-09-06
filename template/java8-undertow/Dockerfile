FROM openjdk:8u131-jdk-alpine as builder

RUN addgroup -S app && adduser -S -g app app \
    && mkdir -p /usr/share/maven \
    && echo "Downloading maven.." \
    && wget http://www-us.apache.org/dist/maven/maven-3/3.6.0/binaries/apache-maven-3.6.0-bin.tar.gz \
    && tar xvzf apache-maven-3.6.0-bin.tar.gz -C /usr/share/maven --strip-components=1 \
    && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn \
    && rm apache-maven-3.6.0-bin.tar.gz
    

ENV MAVEN_HOME /usr/share/maven

RUN mkdir -p /home/app

WORKDIR /home/app

COPY . /home/app
RUN mvn clean package

FROM openjdk:8u131-jdk-alpine as ship
RUN apk --no-cache add curl \
    && echo "Pulling watchdog binary from Github." \
    && curl -sSL https://github.com/openfaas-incubator/of-watchdog/releases/download/0.4.6/of-watchdog > /usr/bin/fwatchdog \
    && chmod +x /usr/bin/fwatchdog \
    && apk del curl

RUN addgroup -S app && adduser -S -g app app
WORKDIR /home/app

COPY ./entry/target/functions.jar /home/app 

RUN chown -R app /home/app
USER app

ENV upstream_url="http://127.0.0.1:8081"
ENV mode="http"

ENV fprocess="java -XX:+UnlockExperimentalVMOptions -XX:+UseCGroupMemoryLimitForHeap -cp ./entry/target/functions.jar io.huduku.openfaas.entrypoint.App"
EXPOSE 8080

HEALTHCHECK --interval=2s CMD [ -e /tmp/.lock ] || exit 1

CMD ["fwatchdog"]


 
