package kz.gm.redislocks;

import kz.gm.redislocks.services.RedissonLockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RedisLocksApplication implements CommandLineRunner {

	@Autowired
	public RedissonLockService redissonLockService;

	public static void main(String[] args) {
		SpringApplication.run(RedisLocksApplication.class, args);
	}

	@Override
	public void run(String... args) {
		System.out.println("=== Testing Locking...");
		redissonLockService.doSomething();
	}
}
