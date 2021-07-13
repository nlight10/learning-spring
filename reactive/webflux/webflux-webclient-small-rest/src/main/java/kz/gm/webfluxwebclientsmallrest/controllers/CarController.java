package kz.gm.webfluxwebclientsmallrest.controllers;

import kz.gm.webfluxwebclientsmallrest.models.Car;
import kz.gm.webfluxwebclientsmallrest.repositories.CarRepository;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Daddy: GM
 * BirthDate: 13.07.2021
 */
@RestController
@RequestMapping("/cars")
public class CarController {

    private CarRepository carRepository;

    public CarController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @GetMapping("/{id}")
    private Mono<Car> getCarById(@PathVariable Long id) {
        return carRepository.findCarById(id);
    }

    @GetMapping
    private Flux<Car> getAllCars() {
        return carRepository.findAllCars();
    }

    @PostMapping("/update")
    private Mono<Car> updateCar(@RequestBody Car car) {
        return carRepository.updateCar(car);
    }

}
