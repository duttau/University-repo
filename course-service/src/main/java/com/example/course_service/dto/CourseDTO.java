package com.example.course_service.dto;

import jdk.jshell.Snippet;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Builder
@Getter
@Setter
public class CourseDTO {
    private Long id;
    private String name;
    private Long departmentId;
    private List<StudentDTO> students;

//    public static Snippet builder() {
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public Long getDepartmentId() {
//        return departmentId;
//    }
//
//    public void setDepartmentId(Long departmentId) {
//        this.departmentId = departmentId;
//    }
//
//    public List<StudentDTO> getStudents() {
//        return students;
//    }
//
//    public void setStudents(List<StudentDTO> students) {
//        this.students = students;
//    }

    public CourseDTO(Long id, String name, Long departmentId, List<StudentDTO> students) {
        this.id = id;
        this.name = name;
        this.departmentId = departmentId;
        this.students = students;
    }
    public CourseDTO(){}
}
