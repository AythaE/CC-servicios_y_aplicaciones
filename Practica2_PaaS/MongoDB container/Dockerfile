FROM mvertes/alpine-mongo:latest
# Reference https://docs.docker.com/engine/reference/builder/#label
LABEL maintainer "AythaE <aythae@correo.ugr.es>"

RUN apk update && apk upgrade && apk add mongodb-tools wget \
    && wget https://raw.githubusercontent.com/mongodb/docs-assets/primer-dataset/primer-dataset.json --no-check-certificate -P /root

COPY mongodb /etc/conf.d/
COPY final_set_up.sh /root/

WORKDIR /root/

ENTRYPOINT [ "/root/run.sh" ]
CMD [ "mongod" ]
