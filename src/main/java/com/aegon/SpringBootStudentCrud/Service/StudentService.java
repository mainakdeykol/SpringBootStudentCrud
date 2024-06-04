package com.aegon.SpringBootStudentCrud.Service;

import com.aegon.SpringBootStudentCrud.Entities.Student;

import java.util.List;

public interface StudentService {
    String addStudent(Student student);
    List<Student> fetchAllStudent();
    Student fetchStudentById(int regId, int rollNo);
    Student fetchStudentByRollNo(int rollNo);
    void deleteStudentById(int regId, int rollNo);
    void deleteStudentByRollNo(int rollNo);
    void deleteStudentAll();
    Student findByFullName(String firstName, String lastName);
    Student findByFirstNameAndRollNo(String firstName, int rollNo);
}
