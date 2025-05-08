package com.example.student_service.service;

import com.example.student_service.dto.CourseDTO;
import com.example.student_service.dto.DepartmentDTO;
import com.example.student_service.dto.StudentDTO;
import com.example.student_service.model.Student;
import com.example.student_service.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    public Student create(Student student) {
        return repository.save(student);
    }

    public List<Student> getAll() {
        return repository.findAll();
    }

    public Optional<Student> getByRollNo(Long rollNo) {
        return repository.findById(rollNo);
    }

    public List<Student> getByName(String name) {
        return repository.findByName(name);
    }

    public List<Student> getByDepartmentId(Long deptId) {
        return repository.findByDepartmentId(deptId);
    }

    public List<Student> getByCourseName(String courseName) {
        return repository.findByEnrolledCoursesContaining(courseName);
    }

    public StudentDTO enrich(Student s) {
        DepartmentDTO dept = restTemplate.getForObject(
                "http://localhost:8081/api/departments/" + s.getDepartmentId(), DepartmentDTO.class);

        List<CourseDTO> courses = new ArrayList<>();
        for (String courseName : s.getEnrolledCourses()) {
            CourseDTO course = restTemplate.getForObject(
                    "http://localhost:8082/api/courses/name/" + courseName, CourseDTO.class);
            courses.add(course);
        }

        return StudentDTO.builder()
                .rollNo(s.getRollNo())
                .name(s.getName())
                .department(dept)
                .courses(courses)
                .build();
    }

    public List<StudentDTO> getAllWithDTOs() {
        List<StudentDTO> list = new ArrayList<>();
        for (Student s : getAll()) {
            list.add(enrich(s));
        }
        return list;
    }

    public List<StudentDTO> getByNameWithDTO(String name) {
        List<StudentDTO> list = new ArrayList<>();
        for (Student s : getByName(name)) {
            list.add(enrich(s));
        }
        return list;
    }
}
