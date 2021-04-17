#!/bin/bash
mkdir forocoin
cd forocoin || exit
git clone https://github.com/CodeURJC-DAW-2020-21/webapp8.git
cd webapp8
git switch predevelop2
cd Backend || exit
# cd Docker || exit
sudo docker build -t marcoszas/webapp8 .
sudo docker-compose up -d
