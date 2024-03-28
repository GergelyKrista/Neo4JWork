package dev.gergely.springbootneo4j.repositories;

import dev.gergely.springbootneo4j.models.User;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.Optional;

public interface UserRepository extends Neo4jRepository<User, Long> {
    Optional<User> findUserByUsername(String username);
}
