package com.example.course_service.controller;

import com.example.course_service.dto.CourseDTO;
import com.example.course_service.model.Course;
import com.example.course_service.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/courses")
public class CourseController {


    @Autowired
    private CourseService service;

    @PostMapping
    public ResponseEntity<Course> create(@RequestBody Course course) {
        return new ResponseEntity<>(service.create(course), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Course> getAll() {
        return service.getAll();
    }

    @GetMapping("{name}")
    public ResponseEntity<CourseDTO> getByName(@PathVariable String name) {
        return ResponseEntity.ok(service.getByName(name));
    }
//    @GetMapping("/name/{name}")
//    public ResponseEntity<?> getByCourseName(@PathVariable String name) {
//        CourseDTO dto = service.getCourseWithDetailsByName(name);
//        return ResponseEntity.ok(dto);
//    }

    @GetMapping("/department/{deptId}")
    public ResponseEntity<List<CourseDTO>> getByDept(@PathVariable Long deptId) {
        return ResponseEntity.ok(service.getCoursesWithStudentsByDept(deptId));
    }
}

