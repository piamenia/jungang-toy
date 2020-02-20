package io.dktechin.common.config.security.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisKeyValueAdapter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import java.time.Duration;

@Configuration
@EnableRedisRepositories(enableKeyspaceEvents = RedisKeyValueAdapter.EnableKeyspaceEvents.ON_STARTUP)
@EnableRedisHttpSession(redisNamespace = "start:session", maxInactiveIntervalInSeconds = 7200)
public class RedisConfiguration {

	@Value("${spring.redis.host}")
	private String host;

	@Value("${spring.redis.port}")
	private int port;

	@Value("${spring.redis.database:0}")
	private int database;

	@Value("${spring.redis.password:#{null}}")
	private String password;

	@Value("${spring.redis.command-timeout:2}")
	private int commandTimeout;

	@Bean
	public RedisConnectionFactory redisConnectionFactory() {

		LettuceClientConfiguration lettuceClientConfiguration = LettuceClientConfiguration.builder()
			.commandTimeout(Duration.ofSeconds(commandTimeout))
			.build();

		RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration(host, port);
		configuration.setDatabase(database);
		RedisPassword redisPassword = RedisPassword.of(password);
		configuration.setPassword(redisPassword);

		return new LettuceConnectionFactory(configuration, lettuceClientConfiguration);
	}

	@Bean
	public RedisTemplate<?, ?> redisTemplate() {
		RedisTemplate<?, ?> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(redisConnectionFactory());
		return redisTemplate;
	}
}
