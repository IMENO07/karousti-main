package edu.usdb.cs.karousti.user.customer;

import edu.usdb.cs.karousti.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor  // Generates a default no-argument constructor
@AllArgsConstructor // Generates a constructor with all fields
@Entity
public class Customer extends User {

    private final String ROLE = "CUSTOMER";

    @Column
    private Integer numberOfOrders;

    @Override
    protected String hasRole() {
        return ROLE;
    }

}
