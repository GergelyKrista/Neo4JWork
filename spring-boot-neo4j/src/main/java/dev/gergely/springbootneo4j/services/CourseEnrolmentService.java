package dev.gergely.springbootneo4j.services;

import dev.gergely.springbootneo4j.models.Course;
import dev.gergely.springbootneo4j.queryresults.CourseEnrolmentQueryResult;
import dev.gergely.springbootneo4j.repositories.CourseRepository;
import dev.gergely.springbootneo4j.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseEnrolmentService {
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public CourseEnrolmentService(CourseRepository courseRepository, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    public Boolean getEnrolmentStatus(String username, String identifier){
        return userRepository.findEnrolmentStatus(username, identifier);
    }

    public CourseEnrolmentQueryResult enrollIn(String username, String identifier){
        return userRepository.createEnrolmentRelationship(username, identifier);
    }

    public List<Course> getAllEnrolledCourseByUsername(String username){
        return courseRepository.findAllEnrolledCoursesByUsername(username);
    }
}

