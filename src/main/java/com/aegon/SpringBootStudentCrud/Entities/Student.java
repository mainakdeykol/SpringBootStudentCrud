package com.aegon.SpringBootStudentCrud.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
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

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public List<StudentMarks> getStudentMarks() {
        return studentMarks;
    }

    public void setStudentMarks(List<StudentMarks> studentMarks) {
        this.studentMarks = studentMarks;
    }
}
