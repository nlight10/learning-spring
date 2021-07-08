package kz.gm.redisembedded;

import kz.gm.redisembedded.config.TestRedisConfiguration;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestRedisConfiguration.class)
class RedisEmbeddedApplicationTests {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@Test
	void testingEmbeddedRedis() {
		String key = "testKey";
		String value = "testValue";
		redisTemplate.opsForValue().set(key, value);

		String gettingValue = redisTemplate.opsForValue().get(key);
		System.out.println("Getting Value = " + gettingValue);
		Assert.assertEquals(value, gettingValue);
	}

}
