//package com.example.course_service.service;
//
//import com.example.course_service.dto.CourseDTO;
//import com.example.course_service.dto.StudentDTO;
//import com.example.course_service.model.Course;
//import com.example.course_service.repository.CourseRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//@Service
//public class CourseService {
//    @Autowired
//    private CourseRepository repository;
//
//    @Autowired
//    private RestTemplate restTemplate;
//
//
//
//    public Course create(Course course) {
//        return repository.save(course);
//    }
//
//    public List<Course> getAll() {
//        return repository.findAll();
//    }
//
//    public CourseDTO getByName(String name) {
//        Course course = repository.findByName(name);
//        return enrichWithStudents(course);
//    }
//
//    public List<Course> getByDepartmentId(Long deptId) {
//        return repository.findByDepartmentId(deptId);
//    }
//
//    public List<CourseDTO> getCoursesWithStudentsByDept(Long deptId) {
//        List<Course> courses = repository.findByDepartmentId(deptId);
//        List<CourseDTO> result = new ArrayList<>();
//        for (Course c : courses) {
//            result.add(enrichWithStudents(c));
//        }
//        return result;
//    }
//
//    private CourseDTO enrichWithStudents(Course course) {
//        List<StudentDTO> students = Arrays.asList(
//                restTemplate.getForObject("http://localhost:8083/api/students/course/" + course.getName(), StudentDTO[].class)
//        );
//
//        return CourseDTO.builder()
//                .id(course.getId())
//                .name(course.getName())
//                .departmentId(course.getDepartmentId())
//                .students(students)
//                .build();
//    }
//}
//


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

    public List<Course> getByDepartmentId(Long deptId) {
        return repository.findByDepartmentId(deptId);
    }

    public List<CourseDTO> getCoursesWithStudentsByDept(Long deptId) {
        List<Course> courses = repository.findByDepartmentId(deptId);
        List<CourseDTO> result = new ArrayList<>();
        for (Course c : courses) {
            result.add(enrichWithStudents(c));
        }
        return result;
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

