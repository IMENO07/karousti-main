package edu.usdb.cs.karousti.auth.requests;

import edu.usdb.cs.karousti.enums.AgentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AgentRegistrationRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private AgentType agentType;
}
