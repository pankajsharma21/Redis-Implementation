package com.Redisimpl.Redis.Implementation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RedisImplementationApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisImplementationApplication.class, args);
	}

}
