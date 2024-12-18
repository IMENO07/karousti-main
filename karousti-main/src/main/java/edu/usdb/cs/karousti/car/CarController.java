package edu.usdb.cs.karousti.car;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.usdb.cs.karousti.auth.AuthenticationResponse;
import edu.usdb.cs.karousti.car.requests.CarRegistrationRequest;
import edu.usdb.cs.karousti.car.services.CarService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequestMapping("/api/v1/car")
@RestController
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @PostMapping("/addCar")
    public ResponseEntity<AuthenticationResponse> addCar(@RequestBody CarRegistrationRequest request) {
        return ResponseEntity.ok(carService.addCar(request));
    }

    @DeleteMapping("/deleteCar")
    public ResponseEntity<AuthenticationResponse> deleteCar(@RequestBody CarRegistrationRequest request) {
        return ResponseEntity.ok(carService.deleteCar(request));
    }

}
