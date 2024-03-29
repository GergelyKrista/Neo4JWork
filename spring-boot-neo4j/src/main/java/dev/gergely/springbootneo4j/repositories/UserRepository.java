package dev.gergely.springbootneo4j.repositories;

import dev.gergely.springbootneo4j.models.User;
import dev.gergely.springbootneo4j.queryresults.CourseEnrolmentQueryResult;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.Optional;

public interface UserRepository extends Neo4jRepository<User, Long> {
    Optional<User> findUserByUsername(String username);

    @Query("MATCH (use:User), (course:Course) WHERE user.username = $username AND course.identifier = $identifier " +
            "RETURN EXISTS((user)-[:ENROLLED_IN]->(course))")
    Boolean findEnrolmentStatus(String username, String identifier);

    @Query("MATCH (use:User), (course:Course) WHERE user.username = $username AND course.identifier = $identifier " +
            "CREATE (user)-[:ENROLLED_IN]->(course) RETURN user, course")
    CourseEnrolmentQueryResult createEnrolmentRelationship(String username, String identifier);
}
