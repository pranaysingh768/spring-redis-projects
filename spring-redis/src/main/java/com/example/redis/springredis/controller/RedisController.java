package com.example.redis.springredis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.redis.springredis.entity.Employee;
import com.example.redis.springredis.service.RedisService;

@RestController
public class RedisController {
	
	@Autowired
	private RedisService redisService;
	
	@PostMapping("/add")
	public Employee assEmployee(@RequestBody Employee employee) {
		return redisService.save(employee);
	}
	
	@GetMapping("/getAll")
	public List<Employee> findEmployeeById(){
		return redisService.findAll();
	}

	
	@GetMapping("/get/{id}")
	public Employee findEmployeeById(@PathVariable Integer id){
		return redisService.findEmployeeById(id);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteEmployee(@PathVariable Integer id) {
		return redisService.deleteEmployee(id);
	}
	
	@PostMapping("/publish")
	public String publishMessage(@RequestBody Employee employee) {
		return redisService.publishMessage(employee);
	}

}
