package com.aegon.SpringBootStudentCrud.Entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class StudentMarks {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String subject;
    private int marks;
}
