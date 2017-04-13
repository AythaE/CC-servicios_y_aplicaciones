#!/bin/sh
mongoimport --db test --collection restaurants --drop --file /root/primer-dataset.json

apk del wget mongodb-tools

rm /root/primer-dataset.json
