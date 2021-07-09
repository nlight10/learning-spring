package kz.gm.redislocks.configs;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Daddy: GM
 * BirthDate: 09.07.2021
 */
@Configuration
public class RedissonConfig {

    @Bean
    public RedissonClient redissonClient(@Value("${spring.redis.host}") String host, @Value("${spring.redis.port}") String port) {
        Config config = new Config();
        String url = "redis://"+ host + ":" + port;
        config.useSingleServer().setAddress(url);
        return Redisson.create(config);
    }
}
