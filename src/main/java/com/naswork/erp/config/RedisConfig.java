package com.naswork.erp.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Program: RedisConfig
 * @Description:
 * @Author: White
 * @DateTime: 2019-04-18 10:32:46
 **/

@Component
@Configuration
public class RedisConfig extends CachingConfigurerSupport {

    @Value("${spring.redis.database}")
    private int database;

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.timeout}")
    private int timeout;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.jedis.pool.maxActive}")
    private long maxActive;

    @Value("${spring.redis.jedis.pool.maxWait}")
    private int maxWaitMillis;

    @Value("${spring.redis.jedis.pool.maxIdle}")
    private int maxIdle;

    @Value("${spring.redis.jedis.pool.minIdle}")
    private int minIdle;

    @Bean
    public JedisPool redisPoolFactory() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxWaitMillis(maxWaitMillis);
        config.setMaxIdle(maxIdle);
        config.setMinIdle(minIdle);
        password = StringUtils.isBlank(password) ? null : password;
        JedisPool jedisPool = new JedisPool(config, host, port, timeout, password, database);
        return jedisPool;
    }

}
