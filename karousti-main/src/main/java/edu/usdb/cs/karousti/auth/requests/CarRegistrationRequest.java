package edu.usdb.cs.karousti.auth.requests;

import edu.usdb.cs.karousti.enums.Availability;
import edu.usdb.cs.karousti.enums.Color;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarRegistrationRequest {
    private String model;
    private String brand;
    private Float price;
    private Color color;
    private int horsepower;
    private Availability availability;
    private String description;
}