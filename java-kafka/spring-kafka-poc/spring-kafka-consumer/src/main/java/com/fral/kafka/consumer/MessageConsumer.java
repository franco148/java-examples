package com.fral.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageConsumer {

    KafkaConsumer<String, String> kafkaConsumer;
    String topicName = "test-topic-replicated";


    public MessageConsumer(Map<String, Object> propsMap) {
        kafkaConsumer = new KafkaConsumer<>(propsMap);
    }


    public static Map<String, Object> buildConsumerProperties() {
        Map<String, Object> propsMap = new HashMap<>();

        propsMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092,localhost:9093,localhost:9094");
        propsMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        propsMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        propsMap.put(ConsumerConfig.GROUP_ID_CONFIG, "msgconsumer"); //Any name

        return propsMap;
    }

    public void pollKafka() {
//        kafkaConsumer.subscribe(List.);
    }

    public static void main(String[] args) {

        MessageConsumer messageConsumer = new MessageConsumer(buildConsumerProperties());
    }
}
