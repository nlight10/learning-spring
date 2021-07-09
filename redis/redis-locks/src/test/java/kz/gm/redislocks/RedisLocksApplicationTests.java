package kz.gm.redislocks;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisServer;

import java.io.IOException;
import java.net.ServerSocket;

@SpringBootTest
class RedisLocksApplicationTests {

	/*private static RedisServer redisServer;
	private static RedissonClient client;
	private static int port;

	@BeforeClass
	public static void setUp() throws IOException {

		// Take an available port
		ServerSocket s = new ServerSocket(0);
		port = s.getLocalPort();
		s.close();

		redisServer = new RedisServer(port);
		redisServer.start();
	}

	@AfterClass
	public static void destroy() {
		redisServer.stop();
		if (client != null) {
			client.shutdown();
		}
	}

	@Test
	public void givenJavaConfig_thenRedissonConnectToRedis() {
		Config config = new Config();
		config.useSingleServer()
				.setAddress(String.format("redis://192.168.4.85:%s", port));

		client = Redisson.create(config);

		assert(client != null && client.getKeys().count() >= 0);
	}*/

}
