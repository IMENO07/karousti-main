package edu.usdb.cs.karousti.car;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.usdb.cs.karousti.enums.Availability;
import edu.usdb.cs.karousti.enums.Color;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    Optional<Car> findByModel(String model);

    Optional<Car> findByBrand(String brand);

    Optional<Car> findByPrice(Float price);

    Optional<Car> findByColor(Color color);

    Optional<Car> findByHorsepower(int horsepower);

    Optional<Car> findByAvailability(Availability availability);

    Optional<Car> findByDescription(String description);
}
