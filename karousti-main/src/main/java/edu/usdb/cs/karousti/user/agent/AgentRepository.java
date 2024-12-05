package edu.usdb.cs.karousti.user.agent;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Integer> {
    Optional<Agent> findByEmail(String email);
}
