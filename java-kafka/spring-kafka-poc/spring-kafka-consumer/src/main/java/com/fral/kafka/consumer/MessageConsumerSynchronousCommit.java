package com.fral.kafka.consumer;

import org.apache.kafka.clients.consumer.CommitFailedException;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageConsumerSynchronousCommit {
	
	private static final Logger logger = LoggerFactory.getLogger(MessageConsumerSynchronousCommit.class);

    KafkaConsumer<String, String> kafkaConsumer;
    String topicName = "test-topic-replicated";


    public MessageConsumerSynchronousCommit(Map<String, Object> propsMap) {
        kafkaConsumer = new KafkaConsumer<>(propsMap);
    }


    public static Map<String, Object> buildConsumerProperties() {
        Map<String, Object> propsMap = new HashMap<>();

        propsMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092,localhost:9093,localhost:9094");
        propsMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        propsMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        propsMap.put(ConsumerConfig.GROUP_ID_CONFIG, "msgconsumer"); //Any name
        // It is going to take previous messages
//        propsMap.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest"); //earliest, latest
        propsMap.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
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
        		ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(timeOutDuration);
            	consumerRecords.forEach((record) -> {
            		String infoMessage = "Consumer Record Key is {} and the value is {} and the partition {}";
            		logger.info(infoMessage, record.key(), record.value(), record.partition());
            	});
            	
            	// This part of the code is after added ENABLE_AUTO_COMMIT_CONFIG
            	if (consumerRecords.count() > 0) {
					kafkaConsumer.commitSync(); // Committed the last record offset returned by the poll.
					logger.info("Offset Committed...");
				}
			}
		} catch (CommitFailedException e) {
			logger.error("CommitFailedException in pollKafka : " + e.getMessage());
		} catch (Exception e) {
			logger.error("Exception in pollKafka : " + e.getMessage());
		} finally {
			kafkaConsumer.close();
		}
    }

    public static void main(String[] args) {

        MessageConsumerSynchronousCommit messageConsumer = new MessageConsumerSynchronousCommit(buildConsumerProperties());
        messageConsumer.pollKafka();
        
        /**
         * auto.offset.reset values can be EARLIEST, LATEST (DEFAULT)
         */
    }
}
