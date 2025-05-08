package com.example.student_service.controller;

import com.example.student_service.dto.StudentDTO;
import com.example.student_service.model.Student;
import com.example.student_service.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/students")
public class StudentController {
    @Autowired
    private StudentService service;

    @PostMapping
    public ResponseEntity<Student> create(@RequestBody Student student) {
        return new ResponseEntity<>(service.create(student), HttpStatus.CREATED);
    }

    @GetMapping
    public List<StudentDTO> getAll() {
        return service.getAllWithDTOs();
    }

    @GetMapping("/{rollNo}")
    public StudentDTO getByRoll(@PathVariable Long rollNo) {
        Student s = service.getByRollNo(rollNo).orElseThrow(() -> new RuntimeException("Not found"));
        return service.enrich(s);
    }

    @GetMapping(params = "name")
    public List<StudentDTO> getByName(@RequestParam String name) {
        return service.getByNameWithDTO(name);
    }

    @GetMapping("/department/{deptId}")
    public List<Student> getByDepartment(@PathVariable Long deptId) {
        return service.getByDepartmentId(deptId);
    }

    @GetMapping("/course/{courseName}")
    public List<Student> getByCourse(@PathVariable String courseName) {
        return service.getByCourseName(courseName);
    }
}
