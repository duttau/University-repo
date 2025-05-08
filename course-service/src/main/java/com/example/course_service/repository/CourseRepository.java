package com.example.course_service.repository;

import com.example.course_service.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Course findByName(String name);
    List<Course> findByDepartmentId(Long deptId);
//    Optional<Course> findByName(String name);
}
