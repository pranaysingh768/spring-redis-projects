package com.example.redis.springredis.entity;


import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@RedisHash("Employee")
public class Employee implements Serializable{
	@Id
	private Integer id;
	private String name;
	private Double salary;
}
