package dev.danielk0121.test.musinsa.shortenurl.config;

import java.time.Duration;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.CacheKeyPrefix;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@EnableCaching
@Configuration
public class RedisCacheManagerConfig {

	@Bean(name = "cacheManager")
	public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
		
		RedisCacheConfiguration configuration = RedisCacheConfiguration
				.defaultCacheConfig()
				.disableCachingNullValues()  // null value 캐시 안함
				.entryTtl(Duration.ofSeconds(60))  // 기본 캐시 시간
				.computePrefixWith(CacheKeyPrefix.simple())
				// redis 캐시 데이터 저장 방식을 StringRedisSerializer 로 설정
				.serializeKeysWith(RedisSerializationContext
						.SerializationPair
						.fromSerializer(new StringRedisSerializer())
						);
		
		return RedisCacheManager.RedisCacheManagerBuilder
				.fromConnectionFactory(connectionFactory)
				.cacheDefaults(configuration)
				.build() ;
				
	}
}
