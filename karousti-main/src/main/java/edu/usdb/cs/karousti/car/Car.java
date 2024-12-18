package edu.usdb.cs.karousti.car;

import edu.usdb.cs.karousti.brand.Brand;
import edu.usdb.cs.karousti.enums.Availability;
import edu.usdb.cs.karousti.enums.Color;
import edu.usdb.cs.karousti.model.Model;
import jakarta.persistence.*;
// import jakarta.persistence.EnumType;
// import jakarta.persistence.Enumerated;
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

    @ManyToOne
    @JoinColumn(name = "model_id", nullable = false)
    private Model model;

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
