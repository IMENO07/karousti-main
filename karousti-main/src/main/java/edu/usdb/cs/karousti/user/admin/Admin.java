package edu.usdb.cs.karousti.user.admin;

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
public class Admin extends User {

    @Column
    private Integer adminLevel;

    private final String ROLE = "ADMIN";

    @Override
    protected String getRole() {
        return ROLE;
    }

}
