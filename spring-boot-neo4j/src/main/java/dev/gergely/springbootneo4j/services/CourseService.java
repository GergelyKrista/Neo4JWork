package dev.gergely.springbootneo4j.services;
import dev.gergely.springbootneo4j.models.Course;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import dev.gergely.springbootneo4j.repositories.CourseRepository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
    public Course getCourseByIdentifier(String identifier) {
        return courseRepository.findCourseByIdentifier(identifier)
                .orElseThrow(()->new ResponseStatusException(HttpStatusCode.valueOf(404), "Course not found"));
    }
}
