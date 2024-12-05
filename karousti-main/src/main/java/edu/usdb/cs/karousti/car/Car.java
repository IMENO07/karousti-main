package edu.usdb.cs.karousti.car;

import edu.usdb.cs.karousti.enums.Availability;
import edu.usdb.cs.karousti.enums.Color;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
// import jakarta.persistence.EnumType;
// import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Car {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "price", nullable = false)
    private Float price;

    // @Enumerated(EnumType.STRING)
    @Column(name = "color", nullable = false)
    private Color color;

    @Column(name = "horsepower", nullable = false)
    private int horsepower;

    // @Enumerated(EnumType.STRING)
    @Column(name = "availability", nullable = false)
    private Availability availability;

    @Column(name = "description")
    private String description;
}
