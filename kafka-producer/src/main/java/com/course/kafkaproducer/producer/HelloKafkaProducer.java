package com.course.kafkaproducer.producer;

import com.course.kafkaproducer.entity.ProducerModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class HelloKafkaProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    ObjectMapper objectMapper;

    //private ObjectMapper objectMapper = new ObjectMapper()

    public void sendJsonMessage(ProducerModel model) throws JsonProcessingException {

        var json = objectMapper.writeValueAsString(model);

        kafkaTemplate.send("t_test", json);
    }
}
