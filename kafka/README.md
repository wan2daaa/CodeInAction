# 테스트 방법 

```shell
docker-compose up -d 
docker-compose exec kafka /bin/bash

cd ../../bin
./kafka-topics --create --topic test --bootstrap-server localhost:9092 --replication-factor 1
./kafka-topics --list --bootstrap-server localhost:9092
exit;
```