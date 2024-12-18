package edu.usdb.cs.karousti.brand;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Brand {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

}
