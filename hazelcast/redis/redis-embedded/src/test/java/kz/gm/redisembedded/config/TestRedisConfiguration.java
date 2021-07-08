package kz.gm.redisembedded.config;

import kz.gm.redisembedded.configs.RedisProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Daddy: GM
 * BirthDate: 08.07.2021
 */
@TestConfiguration
public class TestRedisConfiguration {

    @Value("${spring.redis.port:6380}")
    private int redisPort;

    private RedisServer redisServer;

    @PostConstruct
    public void startRedis() throws IOException, URISyntaxException {

        redisServer = RedisServer.builder()
                .port(redisPort)
                .setting("maxmemory 128M") //maxheap 128M
                .build();
        redisServer.start();
    }

    @PreDestroy
    public void stopRedis() throws InterruptedException {
        if (redisServer != null) {
            redisServer.stop();
        }
    }
}
