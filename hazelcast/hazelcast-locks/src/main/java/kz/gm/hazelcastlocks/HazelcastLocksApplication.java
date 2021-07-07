package kz.gm.hazelcastlocks;

import kz.gm.hazelcastlocks.methods.HazelcastMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HazelcastLocksApplication implements CommandLineRunner {

	@Autowired
	public HazelcastMethod hazelcastMethod;

	public static void main(String[] args) {
		SpringApplication.run(HazelcastLocksApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Testing Hazelcast");
		hazelcastMethod.doSomething();
	}

}
