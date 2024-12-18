package edu.usdb.cs.karousti.model;

import edu.usdb.cs.karousti.brand.Brand;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Model {

    @Id
    private Integer id;

    @ManyToOne
    private Brand brand;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "base_price", nullable = false)
    private Float basePrice;

}
