FROM eclipse-temurin:21.0.10_7-jdk-ubi10-minimal

RUN microdnf install -y curl && microdnf clean all
RUN microdnf install -y jq && microdnf clean all
RUN microdnf install -y dos2unix && microdnf clean all

WORKDIR /home/selenium-docker

ADD target/docker-resources .
ADD runner.sh runner.sh

RUN dos2unix runner.sh

ENTRYPOINT sh runner.sh