
//------------------------------------------------
package com.example.course_service.service;

import com.example.course_service.dto.CourseDTO;
import com.example.course_service.dto.StudentDTO;
import com.example.course_service.model.Course;
import com.example.course_service.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    public Course create(Course course) {
        return repository.save(course);
    }

    public List<Course> getAll() {
        return repository.findAll();
    }

    public CourseDTO getByName(String name) {
        Course course = repository.findByName(name);
        return enrichWithStudents(course);
    }

   

        private CourseDTO enrichWithStudents(Course course) {
        String url = "http://localhost:8083/api/students/course/" + course.getName();

        ResponseEntity<List<StudentDTO>> studentResponse = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<StudentDTO>>() {}
        );

        List<StudentDTO> students = studentResponse.getBody();

        return CourseDTO.builder()
                .id(course.getId())
                .name(course.getName())
                .departmentId(course.getDepartmentId())
                .students(students)
                .build();
    }
}

