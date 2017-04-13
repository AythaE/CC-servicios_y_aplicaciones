FROM python:3.4-alpine
# Reference https://docs.docker.com/engine/reference/builder/#label
LABEL maintainer "AythaE <aythae@correo.ugr.es>"

RUN apk update && apk upgrade \
    && apk add git gcc linux-headers musl-dev supervisor \
    && pip install pymongo==2.8 mongoengine django uwsgi \
    && git clone https://github.com/AythaE/SSBW.git /home/SSBW \
    && apk del git gcc linux-headers musl-dev

EXPOSE 8000

COPY models.py /home/SSBW/T4/app/restaurantes/
COPY settings.py /home/SSBW/T4/app/app/
COPY app.ini /home/SSBW/T4/uwsgi/
COPY supervisord.conf /etc/

CMD ["supervisord", "-n"]
