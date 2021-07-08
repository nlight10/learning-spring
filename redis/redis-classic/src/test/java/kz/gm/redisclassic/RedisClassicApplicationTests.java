package kz.gm.redisclassic;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class RedisClassicApplicationTests {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Test
	void testRedis() {
		String key = "testKey";
		String value = "testValue";
		redisTemplate.opsForValue().set(key, value);

		String gettingValue = (String) redisTemplate.opsForValue().get(key);
		System.out.println("Getting Value = " + gettingValue);
		Assert.assertEquals(value, gettingValue);
	}

}
