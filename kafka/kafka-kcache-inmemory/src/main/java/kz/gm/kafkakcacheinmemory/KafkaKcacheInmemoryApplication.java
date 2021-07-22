package kz.gm.kafkakcacheinmemory;

import io.kcache.Cache;
import io.kcache.KafkaCache;
import org.apache.kafka.common.serialization.Serdes;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class KafkaKcacheInmemoryApplication {

	public static void main(String[] args) throws InterruptedException, IOException {
		//SpringApplication.run(KafkaKcacheInmemoryApplication.class, args);

		String bootstrapServers = "localhost:9092";
		Cache<String, String> cache = new KafkaCache<>(
				bootstrapServers,
				Serdes.String(),  // for serializing/deserializing keys
				Serdes.String()   // for serializing/deserializing values
		);

		cache.init();   // creates topic, initializes cache, consumer, and producer
		cache.put("Kafka", "Rocks");
		String value = cache.get("Kafka");  // returns "Rocks"

		System.out.println("============== HERE WE GO FIRST INSTANCE = " + value);

		Thread.sleep(120000);

		cache.remove("Kafka");
		cache.close();  // shuts down the cache, consumer, and producer
	}

}
