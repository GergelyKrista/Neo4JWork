package dev.gergely.springbootneo4j.controllers;

import dev.gergely.springbootneo4j.models.Course;
import dev.gergely.springbootneo4j.objects.CourseDTO;
import dev.gergely.springbootneo4j.objects.CourseEnrolmentDTO;
import dev.gergely.springbootneo4j.queryresults.CourseEnrolmentQueryResult;
import dev.gergely.springbootneo4j.requests.CourseEnrolmentRequest;
import dev.gergely.springbootneo4j.services.CourseEnrolmentService;
import dev.gergely.springbootneo4j.services.LessonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/enrolments")
public class CourseEnrolmentController {
    private final CourseEnrolmentService courseEnrolmentService;
    private final LessonService lessonService;

    public CourseEnrolmentController(CourseEnrolmentService courseEnrolmentService, LessonService lessonService) {
        this.courseEnrolmentService = courseEnrolmentService;
        this.lessonService = lessonService;
    }

    @GetMapping("/")
    public ResponseEntity<List<CourseDTO>> enrolments(Principal principal){
        List<Course> courses = courseEnrolmentService.getAllEnrolledCourseByUsername(principal.getName());

        List<CourseDTO> responseCourses = courses.stream().map(
                (course) -> {
                    CourseDTO responseCourse = new CourseDTO(course.getIdentifier(), course.getTitle(), course.getTeacher());
                    responseCourse.setLessons(lessonService.getAllLessonsByCourseIdentifier(course.getIdentifier()));
                    responseCourse.setEnrolled(true);

                    return responseCourse;
                }
        ).collect(Collectors.toList());

        return new ResponseEntity<>(responseCourses, HttpStatus.OK);
    }
    @PostMapping("/")
    public ResponseEntity<CourseEnrolmentDTO> enrollIn(@RequestBody CourseEnrolmentRequest request, Principal principal) {
        CourseEnrolmentQueryResult enrolmentQueryResult = courseEnrolmentService.enrollIn(principal.getName(), request.getCourseIdentifier());

        CourseEnrolmentDTO  responseEnrolment= new CourseEnrolmentDTO(
                enrolmentQueryResult.getUser().getUsername(),
                enrolmentQueryResult.getUser().getName(),
                enrolmentQueryResult.getCourse()
        );

        return new ResponseEntity<>(responseEnrolment, HttpStatus.CREATED);
    }
}
