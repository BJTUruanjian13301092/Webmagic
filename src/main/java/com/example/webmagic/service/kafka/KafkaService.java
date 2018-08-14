package com.example.webmagic.service.kafka;

import kafka.admin.AdminUtils;
import kafka.admin.RackAwareMode;
import kafka.utils.ZkUtils;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.KafkaFuture;
import org.apache.kafka.common.Node;
import org.apache.kafka.common.config.ConfigResource;
import org.apache.kafka.common.security.JaasUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutionException;


@Service
public class KafkaService {

    private static AdminClient adminClient = TopicUtil.createAdminClient();

    private static final String TEST_TOPIC = "topic_test";

    /**
     * 获得集群描述信息
     */
    public void describeCluster() throws ExecutionException, InterruptedException {
        DescribeClusterResult ret = adminClient.describeCluster();
        System.out.println(String.format("Cluster id: %s, controller: %s", ret.clusterId().get(), ret.controller().get()));
        System.out.println("Current cluster nodes info: ");
        for (Node node : ret.nodes().get()) {
            System.out.println(node);
        }
    }

    /**
     * 获得Topic描述信息
     */
    public void describeTopics() throws ExecutionException, InterruptedException {
        DescribeTopicsResult ret = adminClient.describeTopics(Arrays.asList(TEST_TOPIC, "__consumer_offsets"));
        Map<String, TopicDescription> topics = ret.all().get();
        for (Map.Entry<String, TopicDescription> entry : topics.entrySet()) {
            System.out.println(entry.getKey() + " ===> " + entry.getValue());
        }
    }

    /**
     * 获得Topic配置信息
     */
    public void describeTopicConfig() throws ExecutionException, InterruptedException {
        DescribeConfigsResult ret = adminClient.describeConfigs(Collections.singleton(new ConfigResource(ConfigResource.Type.TOPIC, TEST_TOPIC)));
        Map<ConfigResource, Config> configs = ret.all().get();
        for (Map.Entry<ConfigResource, Config> entry : configs.entrySet()) {
            ConfigResource key = entry.getKey();
            Config value = entry.getValue();
            System.out.println(String.format("Resource type: %s, resource name: %s", key.type(), key.name()));
            Collection<ConfigEntry> configEntries = value.entries();
            for (ConfigEntry each : configEntries) {
                System.out.println(each.name() + " = " + each.value());
            }
        }
    }

    /**
     * 修改Topic配置信息
     */
    public void alterTopicConfig() throws ExecutionException, InterruptedException {
        Config topicConfig = new Config(Arrays.asList(new ConfigEntry("cleanup.policy", "compact")));
        adminClient.alterConfigs(Collections.singletonMap(
                new ConfigResource(ConfigResource.Type.TOPIC, TEST_TOPIC), topicConfig)).all().get();
    }

    /**
     * 创建Topic
     */
    public void createTopic() throws ExecutionException, InterruptedException {
        NewTopic newTopic = new NewTopic("topic_test", 1, (short)1);
        CreateTopicsResult ret = adminClient.createTopics(Arrays.asList(newTopic));
        ret.all().get();
    }

    /**
     * 删除Topic
     */
    public void deleteTopic() throws ExecutionException, InterruptedException {
        KafkaFuture<Void> futures = adminClient.deleteTopics(Arrays.asList(TEST_TOPIC)).all();
        futures.get();
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
