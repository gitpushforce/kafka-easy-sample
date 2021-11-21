package com.course.kafkaproducer;

import com.course.kafkaproducer.entity.ProducerModel;
import com.course.kafkaproducer.producer.HelloKafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class KafkaProducerApplication implements CommandLineRunner {

	@Autowired
	private HelloKafkaProducer helloKafkaProducer;

	public static void main(String[] args) {
		SpringApplication.run(KafkaProducerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ProducerModel model = new ProducerModel();
		model.setId("s1");
		model.setName("Mike");
		model.setLastname("Myers");
		model.setBirthdate(LocalDate.now());
		helloKafkaProducer.sendJsonMessage (model);
	}
}
