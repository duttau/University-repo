package com.example.student_service.repository;

import com.example.student_service.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface StudentRepository extends JpaRepository<Student, Long> {     List<Student> findByName(String name);
        List<Student> findByDepartmentId(Long deptId);
        List<Student> findByEnrolledCoursesContaining(String courseName);
    }

