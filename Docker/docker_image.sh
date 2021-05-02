#!/bin/bash
cd ../Backend/src/main/resources/static/
rm -r new/
mkdir new
cd ../../../../../Frontend/
ng build --prod --base-href="/new/"
cp -r dist/FrontEndAngular/* ../Backend/src/main/resources/static/new/
cd ../Backend
sudo docker run --rm -v "$PWD":/data -w /data maven mvn package
cp ./target/practicadaw-0.0.1-SNAPSHOT.jar ../Docker
cd ../Docker
sudo docker build -t practicadaw/webapp .
rm -rf *.jar
