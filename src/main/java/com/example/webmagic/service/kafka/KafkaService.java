package com.example.webmagic.service.kafka;

import kafka.admin.AdminUtils;
import kafka.admin.RackAwareMode;
import kafka.utils.ZkUtils;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.security.JaasUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutionException;


@Service
public class KafkaService {

    private static AdminClient adminClient = TopicUtil.createAdminClient();

    /**
     * 创建Topic
     */
    public void createTopic() throws ExecutionException, InterruptedException {
        NewTopic newTopic = new NewTopic("topic_test", 1, (short)1);
        CreateTopicsResult ret = adminClient.createTopics(Arrays.asList(newTopic));
        ret.all().get();
    }

    /**
     * 展示Topic列表
     */
    public void listAllTopics() throws ExecutionException, InterruptedException {
        ListTopicsOptions options = new ListTopicsOptions();
        options.listInternal(true); // includes internal topics such as __consumer_offsets
        ListTopicsResult topics = adminClient.listTopics(options);
        Set<String> topicNames = topics.names().get();
        System.out.println("Current topics in this cluster: " + topicNames);
    }



}
