package com.aegon.SpringBootStudentCrud.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
@Table(name = "Student")
public class Student {
    @Id
    private int rollNo;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String dob;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "rollNo")
    private List<StudentMarks> studentMarks;

}
