package com.example.department_service.controller;

import com.example.department_service.dto.DepartmentDTO;
import com.example.department_service.model.Department;
import com.example.department_service.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/departments")

public class DepartmentController {
    @Autowired
    private DepartmentService service;

    @PostMapping
    public ResponseEntity<Department> create(@RequestBody Department dept) {
        return new ResponseEntity<>(service.create(dept), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAll() {
        return ResponseEntity.ok(service.getAllDepartments());
    }

    @GetMapping("/deptName")
    public ResponseEntity<DepartmentDTO> getByName(@RequestParam String deptName) {
        return ResponseEntity.ok(service.getByName(deptName));
    }
   
}
