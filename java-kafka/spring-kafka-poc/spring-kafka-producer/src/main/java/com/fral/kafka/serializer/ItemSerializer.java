package com.fral.kafka.serializer;

import org.apache.kafka.common.serialization.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fral.kafka.domain.Item;

public class ItemSerializer implements Serializer<Item> {
	
	private static final Logger logger = LoggerFactory.getLogger(ItemSerializer.class);
	
	ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public byte[] serialize(String topic, Item data) {
		
		logger.info("Inside Serialization Logic");
		try {
			return objectMapper.writeValueAsBytes(data);
		} catch (JsonProcessingException e) {
			logger.warn("JsonProcessingException in serialize : {}", data, e);
			return null;
		}
	}

}
