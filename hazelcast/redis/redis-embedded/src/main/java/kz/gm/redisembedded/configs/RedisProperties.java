package kz.gm.redisembedded.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Daddy: GM
 * BirthDate: 08.07.2021
 */
@Configuration
public class RedisProperties {
    private int redisPort;
    private String redisHost;

    public RedisProperties(@Value("${spring.redis.port}") final int redisPort, @Value("${spring.redis.host}") final String redisHost) {
        this.redisPort = redisPort;
        this.redisHost = redisHost;
    }

    public int getRedisPort() {
        return redisPort;
    }

    public String getRedisHost() {
        return redisHost;
    }
}
