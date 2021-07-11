package com.example.redis.springredis.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

public class RedisMessageListener implements MessageListener{

	private static final Logger LOGGER = LoggerFactory.getLogger(RedisMessageListener.class);
	
	@Override
	public void onMessage(Message message, byte[] pattern) {
		LOGGER.info("Consumed Message {}",message);
		
//		try {
//			Employee employee = new ObjectMapper().readValue(message.toString().trim().replaceFirst("\ufeff", ""),Employee.class);
//			LOGGER.info("Name {}",employee.getName());
//		} catch (Exception e) {
//			LOGGER.error("Error in RedisMessageListener :: onMessage");
//			e.printStackTrace();
//		}
	}
}
