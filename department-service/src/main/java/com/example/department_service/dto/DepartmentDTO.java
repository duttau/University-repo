package com.example.department_service.dto;

import java.util.List;

public class DepartmentDTO {
    private Long id;
    private String name;
    private List<CourseDTO> courses;


    private List<Object> students;

    public List<Object> getStudents() {
        return students;
    }

    public void setStudents(List<Object> students) {
        this.students = students;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CourseDTO> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseDTO> courses) {
        this.courses = courses;
    }
}
