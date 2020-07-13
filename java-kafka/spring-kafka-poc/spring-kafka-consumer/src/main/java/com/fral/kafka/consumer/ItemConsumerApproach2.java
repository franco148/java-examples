package com.fral.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fral.kafka.domain.Item;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemConsumerApproach2 {
	
	private static final Logger logger = LoggerFactory.getLogger(ItemConsumerApproach2.class);

    KafkaConsumer<Integer, String> kafkaConsumer;
    String topicName = "items";
    ObjectMapper objectMapper = new ObjectMapper();


    public ItemConsumerApproach2(Map<String, Object> propsMap) {
        kafkaConsumer = new KafkaConsumer<>(propsMap);
    }


    public static Map<String, Object> buildConsumerProperties() {
        Map<String, Object> propsMap = new HashMap<>();

        propsMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092,localhost:9093,localhost:9094");
        propsMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class.getName());
        propsMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        propsMap.put(ConsumerConfig.GROUP_ID_CONFIG, "itemConsumer"); //Any name
        propsMap.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest"); //earliest, latest
        // Here configuring (overriding) the MAX POLL INTERVAL
//        propsMap.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, 5000);
        // If we restart the consumer, it will read again the messages written from 10 seconds ago.
//        propsMap.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 10000);

        return propsMap;
    }

    public void pollKafka() {
        kafkaConsumer.subscribe(List.of(topicName));
        Duration timeOutDuration = Duration.of(100, ChronoUnit.MILLIS);
        
        try {
        	while (true) {
        		ConsumerRecords<Integer, String> consumerRecords = kafkaConsumer.poll(timeOutDuration);
            	consumerRecords.forEach((record) -> {
            		String infoMessage = "Consumer Record Key is {} and the value is {} and the partition {}";
            		logger.info(infoMessage, record.key(), record.value(), record.partition());
            		
            		try {
						Item item = objectMapper.readValue(record.value(), Item.class);
						logger.info("Item : {} ", item);
					} catch (JsonProcessingException e) {
						logger.error("JsonProcessingException in pollKafka ", e);
					}
            	});
			}
		} catch (Exception e) {
			logger.error("Exception in pollKafka : " + e.getMessage());
		} finally {
			kafkaConsumer.close();
		}
    }

    public static void main(String[] args) {

        ItemConsumerApproach2 messageConsumer = new ItemConsumerApproach2(buildConsumerProperties());
        messageConsumer.pollKafka();
        
        /**
         * auto.offset.reset values can be EARLIEST, LATEST (DEFAULT)
         */
    }
}
