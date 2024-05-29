package com.aegon.SpringBootStudentCrud.DTO;

import lombok.Data;

@Data
public class StudentMarksDTO {
    private int id;
    private String subject;
    private int marks;
}
