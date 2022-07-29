package com.example.demo.controller;

import com.example.demo.model.dto.CourseDTO;
import com.example.demo.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/courses")
public class CoursesController {

    private final CourseService courseService;

    public CoursesController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/submit")
    public ResponseEntity submitCourse(@RequestBody CourseDTO courseDTO, UriComponentsBuilder uriComponentsBuilder) {

        long newCourseId = courseService.submitCourse(courseDTO);

        return ResponseEntity.created(uriComponentsBuilder.path("/api/courses/{id}").
                                build(newCourseId))
                .build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCoursesById(@PathVariable("id") Long courseId) {

        Optional<CourseDTO> courseOptional = courseService.getCourseById(courseId);

        if (courseOptional.isEmpty()) {

            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(courseOptional.get());
        }
    }

    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourses() {

        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CourseDTO> deleteCourseById(@PathVariable("id") Long courseId) {
        courseService.deleteCourseById(courseId);

        return ResponseEntity.
                noContent().
                build();
    }

    @PostMapping("/{id}")
    public ResponseEntity<CourseDTO> updateCourse(@PathVariable("id") Long id,
                                                  @RequestBody CourseDTO courseDTO) {

        CourseDTO updatedCourse = courseService.updateCourse(id, courseDTO);

        if (updatedCourse == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedCourse);
    }
}