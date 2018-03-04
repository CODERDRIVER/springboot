package com.example.springbootdemo;

import com.example.springbootdemo.dao.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootdemoApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Test
	public void contextLoads() {


	}

	@Test
	public void testConnection()
	{
		userRepository.deleteAll();
		System.out.println("success");
	}

	@Test
	public void testRedis()
	{
		stringRedisTemplate.opsForValue().set("hello","world");
		System.out.println(stringRedisTemplate.opsForValue().get("hello"));
	}

}
