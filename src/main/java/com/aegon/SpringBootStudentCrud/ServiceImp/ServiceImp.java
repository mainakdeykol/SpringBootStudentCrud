package com.aegon.SpringBootStudentCrud.ServiceImp;

import com.aegon.SpringBootStudentCrud.Entities.Student;
import com.aegon.SpringBootStudentCrud.Repoitory.StudentRepo;
import com.aegon.SpringBootStudentCrud.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceImp implements StudentService {
    @Autowired
    public StudentRepo studentRepo;

    @Override
    public String addStudent(Student student) {
        try {
            studentRepo.save(student);
            return "Success";
        }
        catch (Exception e){
            return e.getMessage();
        }
    }

    @Override
    public List<Student> fetchAllStudent() {
        return studentRepo.findAll();
    }

    @Override
    public Student fetchStudent(int rollNo) {
        Optional<Student> student = studentRepo.findById(rollNo);
        if(student.isEmpty()) return null;
        return student.get();
    }

    @Override
    public void deleteStudentByRollNo(int rollNo) {
        studentRepo.deleteById(rollNo);
    }

    @Override
    public void deleteStudentAll() {
        studentRepo.deleteAll();
    }

    @Override
    public Student findByFullName(String firstName, String lastName) {
        return studentRepo.findByFullName(firstName,lastName);
    }

    @Override
    public Student findByFirstNameAndRollNo(String firstName, int rollNo) {
        return studentRepo.findByFirstNameAndRollNo(firstName,rollNo);
    }
}
