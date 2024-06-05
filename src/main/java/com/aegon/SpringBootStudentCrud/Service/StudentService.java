package com.aegon.SpringBootStudentCrud.Service;

import com.aegon.SpringBootStudentCrud.DTO.StudentRequest;
import com.aegon.SpringBootStudentCrud.DTO.StudentResponse;

import java.util.List;

public interface StudentService {
    String addStudent(StudentRequest studentRequest);
    List<StudentResponse> fetchAllStudent();
    StudentResponse fetchStudentById(int regId, int rollNo);
    StudentResponse fetchStudentByRollNo(int rollNo);
    void deleteStudentById(int regId, int rollNo);
    void deleteStudentByRollNo(int rollNo);
    void deleteStudentAll();
    StudentResponse findByFullName(String firstName, String lastName);
    StudentResponse findByFirstNameAndRollNo(String firstName, int rollNo);
}
