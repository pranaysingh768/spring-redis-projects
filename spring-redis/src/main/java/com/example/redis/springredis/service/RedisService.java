package com.example.redis.springredis.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.redis.springredis.entity.Employee;

@Service
@EnableCaching
public class RedisService {

	@Autowired
	private RedisTemplate redisTemplate;
	
	@Autowired
	private ChannelTopic channelTopic;
	
	private static final String HASH_KEY = "Employee";
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RedisService.class);
	
	public Employee save(Employee employee) {
		redisTemplate.opsForHash().put(HASH_KEY, employee.getId(), employee);
		return employee;
	}
	
	public List<Employee> findAll(){
		return redisTemplate.opsForHash().values(HASH_KEY);
	}
	
	@Cacheable(key = "#id",value = "Employee")
	public Employee findEmployeeById(Integer id) {
		LOGGER.info("In DB");
		return (Employee) redisTemplate.opsForHash().get(HASH_KEY, id);
	}
	
	@CacheEvict(key = "#id",value = "Employee")
	public String deleteEmployee(Integer id) {
		redisTemplate.opsForHash().delete(HASH_KEY, id);
		redisTemplate.delete(id);
		return "Employee removed";
	}
	
	public String publishMessage(Employee employee) {
		redisTemplate.convertAndSend(channelTopic.getTopic(),employee.toString());
		return "Message published !!";
	}
}
