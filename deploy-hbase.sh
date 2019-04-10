#!/bin/bash
# run docker image with h-base
mkdir data
docker run --name=hbase-docker \
    -h hbase-docker -d -v $PWD/data:/data \
    -p 2181:2181 \
    -p 8080:8080 \
    -p 8085:8085 \
    -p 9095:9095 \
    -p 16000-16100:16000-16100 \
    dajobe/hbase

