package kz.gm.webfluxwebsocketsmallrest.clients;

import kz.gm.webfluxwebsocketsmallrest.models.Car;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Daddy: GM
 * BirthDate: 13.07.2021
 */
public class CarWebClient {

    WebClient client = WebClient.create("http://localhost:8080");

    public void consume() {

        Mono<Car> carMono = client.get()
                .uri("/cars/{id}", "1")
                .retrieve()
                .bodyToMono(Car.class);

        carMono.subscribe(System.out::println);

        Flux<Car> carFlux = client.get()
                .uri("/cars")
                .retrieve()
                .bodyToFlux(Car.class);

        carFlux.subscribe(System.out::println);
    }
}
