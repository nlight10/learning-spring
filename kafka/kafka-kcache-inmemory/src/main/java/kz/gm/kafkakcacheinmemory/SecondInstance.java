package kz.gm.kafkakcacheinmemory;

import io.kcache.Cache;
import io.kcache.KafkaCache;
import org.apache.kafka.common.serialization.Serdes;

import java.io.IOException;

/**
 * Daddy: GM
 * BirthDate: 22.07.2021
 */
public class SecondInstance {

    public static void main(String[] args) throws InterruptedException, IOException {

        String bootstrapServers = "localhost:9092";
        Cache<String, String> cache = new KafkaCache<>(
                bootstrapServers,
                Serdes.String(),  // for serializing/deserializing keys
                Serdes.String()   // for serializing/deserializing values
        );

        cache.init();   // creates topic, initializes cache, consumer, and producer
        //cache.put("Kafka", "Rocks");
        String value = cache.get("Kafka");  // returns "Rocks"

        System.out.println("============== HERE WE GO SECOND INSTANCE = " + value);

        Thread.sleep(120000);

        cache.remove("Kafka");
        cache.close();  // shuts down the cache, consumer, and producer

    }
}
