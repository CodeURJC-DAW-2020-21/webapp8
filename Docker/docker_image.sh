#!/bin/bash
sudo docker run --rm -v "$PWD":/data -w /data maven mvn package
sudo docker build -t practicadaw/webapp .