package com.fral.kafka.producer;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class MessageProducer {

    private static final Logger logger = LoggerFactory.getLogger(MessageProducer.class);

    String topicName = "test-topic";
    KafkaProducer<String, String> kafkaProducer;


    public MessageProducer(Map<String, Object> propsMap) {
        kafkaProducer = new KafkaProducer<>(propsMap);
    }



    Callback callback = (metadata, exception) -> {
    	if (exception != null) {
			logger.error("Error in Callback is {} ", exception.getMessage());
		} else {
			logger.info("Published Message Offset in Callback is {} and the partition is {}", metadata.offset(), metadata.partition());
		}
    };
    
    public static Map<String, Object> propsMap() {
        Map<String, Object> propsMap = new HashMap<>();
        propsMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        propsMap.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        propsMap.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        return propsMap;
    }
    
    public void publishMessageAsync(String key, String value) {
    	ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topicName, key, value);
    	kafkaProducer.send(producerRecord, callback);
    }

    public void publishMessageSync(String key, String value) {
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topicName, key, value);
        try {
            RecordMetadata recordMetadata = kafkaProducer.send(producerRecord).get();
//            System.out.println("partition " + recordMetadata.partition() + " , offset: " + recordMetadata.offset());
            logger.info("Message {} sent successfully for the key {}", value, key);
            logger.info("Published Message Offset is {} and the partition is {}", recordMetadata.offset(), recordMetadata.partition());
        } catch (InterruptedException | ExecutionException e) {
            logger.error("Exception in publishMessageSync : {}", e.getMessage());
        }
    }

    public static void main(String[] args) throws InterruptedException {

        MessageProducer messageProducer = new MessageProducer(propsMap());
//        messageProducer.publishMessageSync(null, "ABC");
        messageProducer.publishMessageAsync(null, "EEEFFFCCC - Async");
        Thread.sleep(3000);
    }
}
