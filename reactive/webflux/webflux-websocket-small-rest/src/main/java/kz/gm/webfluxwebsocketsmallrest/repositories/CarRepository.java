package kz.gm.webfluxwebsocketsmallrest.repositories;

import kz.gm.webfluxwebsocketsmallrest.models.Car;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * Daddy: GM
 * BirthDate: 13.07.2021
 */
@Repository
public class CarRepository {

    static Map<Long, Car> carData;
    static Map<Long, String> carAccessData;

    static
    {
        carData = new HashMap<>();
        carData.put(1L, new Car(1L, "Car 1"));
        carData.put(2L, new Car(2L, "Car 2"));
        carData.put(3L, new Car(3L, "Car 3"));
        carData.put(4L, new Car(4L, "Car 4"));
        carData.put(5L, new Car(5L, "Car 5"));

        carAccessData = new HashMap<>();
        carAccessData.put(1L, "Car 1 Access Key");
        carAccessData.put(2L, "Car 2 Access Key");
        carAccessData.put(3L, "Car 3 Access Key");
        carAccessData.put(4L, "Car 4 Access Key");
        carAccessData.put(5L, "Car 5 Access Key");
    }

    public Mono<Car> findCarById(Long id) {
        return Mono.just(carData.get(id));
    }

    public Flux<Car> findAllCars() {
        return Flux.fromIterable(carData.values());
    }

    public Mono<Car> updateCar(Car car) {
        Car existingCar = carData.get(car.getId());
        if (existingCar != null) {
            existingCar.setName(car.getName());
        }
        return Mono.just(existingCar);
    }
}
