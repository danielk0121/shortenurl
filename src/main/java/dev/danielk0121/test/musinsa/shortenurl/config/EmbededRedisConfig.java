package dev.danielk0121.test.musinsa.shortenurl.config;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;
import redis.embedded.RedisServer;

@Slf4j
@Configuration
public class EmbededRedisConfig {
	
	@Value("${spring.redis.port}") private int redisPort;
	
	private RedisServer redisServer;
	
	@PostConstruct
	public void redisServer() {
		redisServer = new RedisServer(redisPort);
		redisServer.start();
		log.info("redis start");
	}
	
	@PreDestroy
	public void stopRedis() {
		if(redisServer != null) {
			redisServer.stop();
		}
		log.info("redis stop");
	}
}
