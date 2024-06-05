package com.aegon.SpringBootStudentCrud.DTO;

import com.aegon.SpringBootStudentCrud.Entities.StudentIdentity;
import com.aegon.SpringBootStudentCrud.Entities.StudentMarks;
import lombok.*;

import java.util.List;

@Data
public class StudentResponse {
    private StudentIdentity id;
    private String firstName;
    private String lastName;
    private String dob;
    private List<StudentMarks> studentMarks;
}
