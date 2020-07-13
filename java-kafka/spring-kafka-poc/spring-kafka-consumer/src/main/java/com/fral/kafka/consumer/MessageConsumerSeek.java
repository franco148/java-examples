package com.fral.kafka.consumer;

import org.apache.kafka.clients.consumer.CommitFailedException;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fral.kafka.listeners.MessageRebalanceListener;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageConsumerSeek {
	
	private static final Logger logger = LoggerFactory.getLogger(MessageConsumerSeek.class);

    KafkaConsumer<String, String> kafkaConsumer;
    String topicName = "test-topic-replicated";
    public static final String serialiaziedFilePath = "spring-kafka-consumer/src/main/resources/offset.ser";
    
    
    private Map<TopicPartition, OffsetAndMetadata> offsetMap = new HashMap<TopicPartition, OffsetAndMetadata>();


    public MessageConsumerSeek(Map<String, Object> propsMap) {
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
        kafkaConsumer.subscribe(List.of(topicName), new MessageRebalanceListener(kafkaConsumer));
        Duration timeOutDuration = Duration.of(100, ChronoUnit.MILLIS);
        
        try {
        	while (true) {
        		ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(timeOutDuration);
            	consumerRecords.forEach((record) -> {
            		String infoMessage = "Consumer Record Key is {} and the value is {} and the partition {}";
            		logger.info(infoMessage, record.key(), record.value(), record.partition());
            		
            		// When we are handling this operation manually, we need to add +1 to the offset.
            		offsetMap.put(new TopicPartition(record.topic(), record.partition()), new OffsetAndMetadata(record.offset() + 1, null));
            	});
            	
            	// This part of the code is after added ENABLE_AUTO_COMMIT_CONFIG
            	if (consumerRecords.count() > 0) {
					//kafkaConsumer.commitSync(offsetMap); // Committed the last record offset returned by the poll.
					logger.info("Offset Committed...");
					writeOffsetsMapToPath(offsetMap);
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
    
    private void writeOffsetsMapToPath(Map<TopicPartition, OffsetAndMetadata> offsetsMap) throws IOException {

        FileOutputStream fout = null;
        ObjectOutputStream oos = null;
        try {
            fout = new FileOutputStream(serialiaziedFilePath);
            oos = new ObjectOutputStream(fout);
            oos.writeObject(offsetsMap);
            logger.info("Offsets Written Successfully!");
        } catch (Exception ex) {
            logger.error("Exception Occurred while writing the file : " + ex);
        } finally {
            if(fout!=null)
                fout.close();
            if(oos!=null)
                oos.close();
        }
    }

    public static void main(String[] args) {

        MessageConsumerSeek messageConsumer = new MessageConsumerSeek(buildConsumerProperties());
        messageConsumer.pollKafka();
        
        /**
         * auto.offset.reset values can be EARLIEST, LATEST (DEFAULT)
         */
    }
}
