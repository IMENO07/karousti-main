package edu.usdb.cs.karousti.user.agent;

import edu.usdb.cs.karousti.enums.AgentType;
import edu.usdb.cs.karousti.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor // Generates a default no-argument constructor
@AllArgsConstructor // Generates a constructor with all fields
@Entity
public class Agent extends User {
    @Column
    private AgentType agentType;

    private final String ROLE = "AGENT";

    @Override
    protected String getRole() {
        return ROLE;
    }
}
