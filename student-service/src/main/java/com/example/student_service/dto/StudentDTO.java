package com.example.student_service.dto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDTO {
    private Long rollNo;
    private String name;
    private DepartmentDTO department;
    private List<CourseDTO> courses;
}
