#!/bin/bash
docker build -t interscsimulator .
for i in {0..20};
do
 echo “$i”
 config="/src/mock-simulators/smart_city_model/scenario2/config$i.xml"
 volume="/home/eduardo/experiments/volume$i"

 echo "$config"

 docker run -t -w /src/mock-simulators/smart_city_model/src -h teste.com -v $volume:/src/mock-simulators/smart_city_model/output -e USER=root -e CONFIG_PATH=$config interscsimulator
done



#docker run -t -w /src/mock-simulators/smart_city_model/src -h teste.com -v /home/eduardo/experiments/vteste:/src/mock-simulators/smart_city_model/output -e USER=root -e CONFIG_PATH=/src/mock-simulators/smart_city_model/base_scenario/config.xml interscsimulator