package com.course.kafkaconsumer.consumer;

import com.course.kafkaconsumer.model.ConsumerModel;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class HelloKafkaConsumer {

    @Autowired
    private ObjectMapper objectMapper;

    private static final Logger log = LoggerFactory.getLogger(HelloKafkaConsumer.class);

    @KafkaListener(topics = "t_test")
    public void consume(String message) throws JsonParseException, JsonMappingException, IOException {
        var dataFromJson = objectMapper.readValue(message, ConsumerModel.class);
        System.out.println(dataFromJson);
        log.info("Person data is {}", message);
    }
}
