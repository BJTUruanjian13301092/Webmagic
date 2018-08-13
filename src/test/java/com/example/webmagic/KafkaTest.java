package com.example.webmagic;

import com.example.webmagic.service.kafka.KafkaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.ExecutionException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WebmagicApplication.class)
public class KafkaTest {

    @Autowired
    KafkaService kafkaService;

//    @Test
//    public void createTopic() throws ExecutionException, InterruptedException {
//        kafkaService.createTopic();
//    }
//
//    @Test
//    public void listAllTopics() throws ExecutionException, InterruptedException {
//        kafkaService.listAllTopics();
//    }
//
//    @Test
//    public void describeCluster() throws ExecutionException, InterruptedException {
//        kafkaService.describeCluster();
//    }
//
//    @Test
//    public void describeTopics() throws ExecutionException, InterruptedException {
//        kafkaService.describeTopics();
//    }
//
//    @Test
//    public void describeTopicConfig() throws ExecutionException, InterruptedException {
//        kafkaService.describeTopicConfig();
//    }

}
