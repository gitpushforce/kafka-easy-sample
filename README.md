# kafka-easy-sample
easy sample kafka json message 

1) docker-compose.yml File to get apache kafka installed in docker

```
version: "3.7"

networks:
  kafka-net:
    name: kafka-net
    driver: bridge

services:
  zookeeper:
    image: zookeeper:3.7.0
    container_name: zookeeper
    restart: always
    networks:
      - kafka-net
    ports:
      - "2181:2181"

  kafka:
    image: wurstmeister/kafka:2.13-2.7.0
    container_name: kafka
    restart: always
    networks:
      - kafka-net
    ports:
      - "9092:9092"
    environment:
      KAFKA_LISTENER_SECURITY_PROTOCOL_MAP: DOCKER_INTERNAL:PLAINTEXT,DOCKER_EXTERNAL:PLAINTEXT
      KAFKA_LISTENERS: DOCKER_INTERNAL://:29092,DOCKER_EXTERNAL://:9092
      KAFKA_ADVERTISED_LISTENERS: DOCKER_INTERNAL://kafka:29092,DOCKER_EXTERNAL://${DOCKER_HOST_IP:-127.0.0.1}:9092
      KAFKA_INTER_BROKER_LISTENER_NAME: DOCKER_INTERNAL
      KAFKA_ZOOKEEPER_CONNECT: "zookeeper:2181"
      KAFKA_BROKER_ID: 1
      KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1
    depends_on:
      - zookeeper

```

2) Access to the docker container

```
   docker exec -it kafka bash
```
   
2) create topic with name t_test  (change it for your own)
 
```
kafka-topics.sh --bootstrap-server localhost:9092 --create --topic t_test --partitions 1 --replication-factor 1
```

3) run kafka-consumer first, then run kafka-producer, check logs in consumer's console.
   It should return the Json constructed in kafka-producer.
   
   this running order is because kafka will read the latest message by default. If we run producer first and then consumer, the message will be old already and consumer will wait for the new latest one. 
   If we want to make the consumer read all messages from the begin we should add the line below to the application.properties file
```
   spring.kafka.consumer.auto-offset-reset=earliest
```
