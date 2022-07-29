package com.example.demo.service;

import com.example.demo.model.dto.CourseDTO;
import com.example.demo.model.entity.CourseEntity;
import com.example.demo.repository.CourseRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {

    public final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Optional<CourseDTO> getCourseById(Long id) {

        return courseRepository.findById(id)
                .map(this::map);
    }

    public List<CourseDTO> getAllCourses() {

        return courseRepository.findAll()
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    public void deleteCourseById(Long courseId) {

        try {
            this.courseRepository.deleteById(courseId);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
    }

//    public void submitCourse(CourseDTO courseDTO) {
//
//        CourseEntity course = new CourseEntity();
//        course.setTitle(courseDTO.getTitle());
//        course.setLecturer(courseDTO.getLecturer());
//        course.setRoom(courseDTO.getRoom());
//
//        courseRepository.save(course);
//    }

    public Long submitCourse(CourseDTO courseDTO) {

        CourseEntity course = new CourseEntity();
        course.setTitle(courseDTO.getTitle());
        course.setLecturer(courseDTO.getLecturer());
        course.setRoom(courseDTO.getRoom());

        courseRepository.save(course);

        return course.getId();
    }

    public CourseDTO updateCourse(Long id, CourseDTO courseDTO) {

        Optional<CourseEntity> courseOpt = courseRepository.findById(id);

        if (courseOpt.isEmpty()) {
            return null;
        }

        CourseEntity courseEntity = courseOpt.get();

        updateCourseEntity(courseEntity, courseDTO);

        courseRepository.save(courseEntity);

        return map(courseEntity);
    }

    private void updateCourseEntity(CourseEntity courseEntity, CourseDTO courseDTO) {

        String title = courseDTO.getTitle();

        if (title != null) {
            courseEntity.setTitle(title);
        }

        String lecturer = courseDTO.getLecturer();

        if (lecturer != null) {
            courseEntity.setLecturer(lecturer);
        }

        Integer room = courseDTO.getRoom();

        if (room != null) {
            courseEntity.setRoom(room);
        }
    }

    private CourseDTO map(CourseEntity courseEntity) {

        CourseDTO mappedCourse = new CourseDTO();
        mappedCourse.setTitle(courseEntity.getTitle());
        mappedCourse.setRoom(courseEntity.getRoom());
        mappedCourse.setLecturer(courseEntity.getLecturer());

        return mappedCourse;
    }
}
