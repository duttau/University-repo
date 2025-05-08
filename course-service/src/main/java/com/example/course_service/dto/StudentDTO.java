package com.example.course_service.dto;

import lombok.Builder;

import java.util.List;

@Builder
public class StudentDTO {
    private Long rollNo;
    private String name;
    private Long departmentId;
    private List<String> enrolledCourses;

    public Long getRollNo() {
        return rollNo;
    }

    public void setRollNo(Long rollNo) {
        this.rollNo = rollNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public List<String> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void setEnrolledCourses(List<String> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }

    public StudentDTO(Long rollNo, String name, Long departmentId, List<String> enrolledCourses) {
        this.rollNo = rollNo;
        this.name = name;
        this.departmentId = departmentId;
        this.enrolledCourses = enrolledCourses;
    }
    StudentDTO(){}
}
