package com.example.webmagic.service.kafka;

import org.apache.kafka.clients.producer.*;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class MyKafkaProducer {

    private final static String TOPIC = "topic_test";

    private Producer<String, String> producer;

    private MyKafkaProducer() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<String, String>(props);
    }

    public void produceMyMessage(){
        ProducerRecord<String, String> msg = new ProducerRecord<String, String>(TOPIC, "hahaha");
        //这个回调可以不加~
        producer.send(msg, new Callback() {
            public void onCompletion(RecordMetadata metadata, Exception e) {
                if(e != null) {
                    e.printStackTrace();
                } else {
                    System.out.println("The offset of the record we just sent is: " + metadata.offset());
                }
            }
        });
    }
}
