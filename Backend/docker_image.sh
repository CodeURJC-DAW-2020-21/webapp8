#!/bin/bash
mkdir forocoin
cd forocoin || exit
git clone https://github.com/CodeURJC-DAW-2020-21/webapp8.git
cd webapp8/Backend || exit
cd Docker || exit
sudo docker-compose up -d
