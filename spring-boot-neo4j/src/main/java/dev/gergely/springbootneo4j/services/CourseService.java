package dev.gergely.springbootneo4j.services;
import dev.gergely.springbootneo4j.models.Course;
import org.springframework.stereotype.Service;
import dev.gergely.springbootneo4j.repositories.CourseRepository;

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
}
