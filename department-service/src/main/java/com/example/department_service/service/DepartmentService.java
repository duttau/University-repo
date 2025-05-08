package com.example.department_service.service;

import com.example.department_service.dto.CourseDTO;
import com.example.department_service.dto.DepartmentDTO;
import com.example.department_service.model.Department;
import com.example.department_service.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository repository;

    @Autowired
    private RestTemplate restTemplate;

    public Department create(Department dept) {
        return repository.save(dept);
    }

    public List<DepartmentDTO> getAllDepartments() {
        List<Department> depts = repository.findAll();
        List<DepartmentDTO> result = new ArrayList<>();

        for (Department dept : depts) {
            DepartmentDTO dto = getDepartmentWithCourses(dept);
            result.add(dto);
        }
        return result;
    }

    public DepartmentDTO getById(Long id) {
        Optional<Department> optional = repository.findById(id);
        if (!optional.isPresent()) throw new RuntimeException("Department not found");

        Department dept = optional.get();

        DepartmentDTO dto = new DepartmentDTO();
        dto.setId(dept.getId());
        dto.setName(dept.getName());

        // Get courses and students via REST
        dto.setCourses(restTemplate.getForObject("http://localhost:8082/api/courses/department/" + id, List.class));
        dto.setStudents(restTemplate.getForObject("http://localhost:8083/api/students/department/" + id, List.class));

        return dto;
    }

    public DepartmentDTO getByName(String deptName) {
        Department dept = repository.findByName(deptName);
        return getDepartmentWithCourses(dept);
    }

    private DepartmentDTO getDepartmentWithCourses(Department dept) {
        DepartmentDTO dto = new DepartmentDTO();
        dto.setId(dept.getId());
        dto.setName(dept.getName());

        // Call CourseService to get course details
        CourseDTO[] courses = restTemplate.getForObject(
                "http://localhost:8082/api/courses/department/" + dept.getId(),
                CourseDTO[].class
        );
        if (courses != null) {
            dto.setCourses(Arrays.asList(courses));
        }

        return dto;
    }
}
