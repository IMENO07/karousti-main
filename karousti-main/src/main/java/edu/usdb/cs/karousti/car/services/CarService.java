package edu.usdb.cs.karousti.car.services;

import java.util.List;

import org.springframework.stereotype.Service;

import edu.usdb.cs.karousti.auth.AuthenticationResponse;
import edu.usdb.cs.karousti.auth.requests.CarRegistrationRequest;
import edu.usdb.cs.karousti.car.Car;
import edu.usdb.cs.karousti.car.CarRepository;

@Service
public class CarService {
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car getCarByModel(String model) {
        return carRepository.findByModel(model).orElse(null);
    }

    public Car getCarByBrand(String brand) {
        return carRepository.findByBrand(brand).orElse(null);
    }

    public AuthenticationResponse addcar(CarRegistrationRequest request) {
        Car car = Car.builder()
                .model(request.getModel())
                .brand(request.getBrand())
                .price(request.getPrice())
                .color(request.getColor())
                .horsepower(request.getHorsepower())
                .availability(request.getAvailability())
                .description(request.getDescription())
                .build();
        carRepository.save(car);
        return new AuthenticationResponse("Car added successfully");
    }

    public AuthenticationResponse deleteCar(CarRegistrationRequest request) {
        Car car = carRepository.findByModel(request.getModel()).orElse(null);
        if (car != null) {
            carRepository.delete(car);
            return new AuthenticationResponse("Car deleted successfully");
        } else {
            return new AuthenticationResponse("Car not found");
        }
    }

}
