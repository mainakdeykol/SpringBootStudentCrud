package com.aegon.SpringBootStudentCrud.DTO;

import com.aegon.SpringBootStudentCrud.Entities.StudentMarks;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
public class StudentDTO {
    private int rollNo;
    private String firstName;
    private String lastName;
    private String dob;
    private List<StudentMarks> studentMarks;
}
