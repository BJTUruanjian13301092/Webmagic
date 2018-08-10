package com.example.webmagic.controller;

import com.example.webmagic.service.kafka.MyKafkaConsumer;
import com.example.webmagic.service.kafka.MyKafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class KafkaController {

    @Autowired
    MyKafkaConsumer myKafkaConsumer;

    @Autowired
    MyKafkaProducer myKafkaProducer;

    @RequestMapping("/produce")
    public void kafkaProduce(){
        myKafkaProducer.produceMyMessage();
    }

    @RequestMapping("/consume")
    public void kafkaConsume(){
        myKafkaConsumer.consumeMyMessage();
    }
}
