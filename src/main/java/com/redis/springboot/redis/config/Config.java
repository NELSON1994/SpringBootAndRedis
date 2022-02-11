package com.redis.springboot.redis.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableRedisRepositories
public class Config {

    //connecting using Jedis
//    @Bean
//    public JedisConnectionFactory redisConnectionFactoryWithJedis() {
//        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
//        jedisConnectionFactory.setHostName("127.0.0.1");
//        jedisConnectionFactory.setPort(6379);
//        jedisConnectionFactory.afterPropertiesSet();
//        return jedisConnectionFactory;
//    }
//
//    //Connecting using lettuce
//    @Bean
//    public LettuceConnectionFactory redisConnectionFactoryWithLettuce() {
//        LettuceConnectionFactory lcf = new LettuceConnectionFactory();
//        lcf.setHostName("127.0.0.1");
//        lcf.setPort(6379);
//        lcf.afterPropertiesSet();
//        return lcf;
//    }
//
//    @Bean
//    public RedisTemplate<String, SecurityProperties.User> redisTemplate() {
//        RedisTemplate<String, SecurityProperties.User> redisTemplate = new RedisTemplate<String , SecurityProperties.User>();
//        redisTemplate.setConnectionFactory(redisConnectionFactoryWithJedis());
//        redisTemplate.afterPropertiesSet();
//        return redisTemplate;
//    }


    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration=new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName("localhost");
        redisStandaloneConfiguration.setPort(6379);
        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        final RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new JdkSerializationRedisSerializer());
        template.setValueSerializer(new JdkSerializationRedisSerializer());
        template.setEnableTransactionSupport(true);
        template.afterPropertiesSet();
        return template;
    }
}
