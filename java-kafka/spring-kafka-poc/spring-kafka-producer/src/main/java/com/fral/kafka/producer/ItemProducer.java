package com.fral.kafka.producer;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fral.kafka.domain.Item;
import com.fral.kafka.serializer.ItemSerializer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class ItemProducer {

    private static final Logger logger = LoggerFactory.getLogger(ItemProducer.class);

    String topicName = "items";
    KafkaProducer<Integer, Item> kafkaProducer;


    public ItemProducer(Map<String, Object> propsMap) {
        kafkaProducer = new KafkaProducer<Integer, Item>(propsMap);
    }



    Callback callback = (metadata, exception) -> {
    	if (exception != null) {
			logger.error("Error in Callback is {} ", exception.getMessage());
		} else {
			logger.info("Published Message Offset in Callback is {} and the partition is {}", metadata.offset(), metadata.partition());
		}
    };
    
    public void close(){
        kafkaProducer.close();
    }
    
    public static Map<String, Object> propsMap() {
        Map<String, Object> propsMap = new HashMap<>();
        propsMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092,localhost:9093,localhost:9094");
        propsMap.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        propsMap.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ItemSerializer.class.getName());
        propsMap.put(ProducerConfig.ACKS_CONFIG, "all");
        propsMap.put(ProducerConfig.RETRIES_CONFIG, 10); //How many times
        propsMap.put(ProducerConfig.RETRY_BACKOFF_MS_CONFIG, 3000); //Every 3 seconds

        return propsMap;
    }
    
//    public void publishMessageAsync(String key, String value) {
//    	ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topicName, key, value);
//    	kafkaProducer.send(producerRecord, callback);
//    }

    public void publishMessageSync(Item item) {
        ProducerRecord<Integer, Item> producerRecord = new ProducerRecord<>(topicName, item.getId(), item);
        try {
            RecordMetadata recordMetadata = kafkaProducer.send(producerRecord).get();
//            System.out.println("partition " + recordMetadata.partition() + " , offset: " + recordMetadata.offset());
            logger.info("Message {} sent successfully for the key {}", item, item.getId());
            logger.info("Published Message Offset is {} and the partition is {}", recordMetadata.offset(), recordMetadata.partition());
        } catch (InterruptedException | ExecutionException e) {
            logger.error("Exception in publishMessageSync : {}", e.getMessage());
        }
    }

    public static void main(String[] args) throws InterruptedException {

        ItemProducer itemProducer = new ItemProducer(propsMap());
        
        List<Item> items = List.of
		(
			new Item(1, "LG TV", 200.00),
            new Item(2, "Iphone 10 Pro Max", 949.99));
	        
        	items.forEach((item -> {
	            itemProducer.publishMessageSync(item);
	        })
		);
    }
}
