package me.glinde.blog.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class JedisConfig{

    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.password}")
    private String password;
    @Value("${spring.redis.timeout}")
    private int timeout;
    @Value("${spring.redis.jedis.pool.max-active}")
    private int maxActive;
    @Value("${spring.redis.jedis.pool.max-idle}")
    private int maxIdle;
    @Value("${spring.redis.jedis.pool.max-wait}")
    private long maxWait;
    @Value("${spring.redis.jedis.pool.max-idle}")
    private int minIdle;

    @Bean
    public JedisPool jedisProvider() {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxIdle(maxIdle);
            config.setMaxWaitMillis(maxWait);
            config.setMaxTotal(maxActive);
            config.setMinIdle(minIdle);
            JedisPool jedisPool = new JedisPool(config, host, port, timeout, password);

            System.out.println("连接redis" + jedisPool.getResource().toString());

            return jedisPool;
        }catch (Exception e){
            System.out.println("redis连接失败");
            return null;
        }
    }
}