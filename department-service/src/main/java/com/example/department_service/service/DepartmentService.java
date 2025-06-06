package com.example.department_service.service;

import com.example.department_service.dto.CourseDTO;
import com.example.department_service.dto.DepartmentDTO;
import com.example.department_service.dto.StudentDTO;
import com.example.department_service.model.Department;
import com.example.department_service.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
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
            result.add(getDepartmentWithCoursesAndStudents(dept));
        }
        return result;
    }

        public DepartmentDTO getByName(String deptName) {
        Optional<Department> optional = repository.findByName(deptName);
        if (!optional.isPresent()) throw new RuntimeException("Department not found");
        return getDepartmentWithCoursesAndStudents(optional.get());
    }

    private DepartmentDTO getDepartmentWithCoursesAndStudents(Department dept) {
        DepartmentDTO dto = new DepartmentDTO();
        dto.setId(dept.getId());
        dto.setName(dept.getName());

        // Get courses for department
        ResponseEntity<List<CourseDTO>> courseResponse = restTemplate.exchange(
                "http://localhost:8082/api/courses/department/{deptId}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CourseDTO>>() {},
                dept.getId()
        );
        dto.setCourses(courseResponse.getBody());

        // Get students for department
        ResponseEntity<List<StudentDTO>> studentResponse = restTemplate.exchange(
                "http://localhost:8083/api/students/department/{deptId}",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<StudentDTO>>() {},
                dept.getId()
        );
        dto.setStudents(studentResponse.getBody());

        return dto;
    }
}

