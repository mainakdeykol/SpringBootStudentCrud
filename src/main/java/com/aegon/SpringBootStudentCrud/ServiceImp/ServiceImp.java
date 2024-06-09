package com.aegon.SpringBootStudentCrud.ServiceImp;

import com.aegon.SpringBootStudentCrud.CustomException.InternalServerError;
import com.aegon.SpringBootStudentCrud.DTO.StudentRequest;
import com.aegon.SpringBootStudentCrud.DTO.StudentResponse;
import com.aegon.SpringBootStudentCrud.Entities.Student;
import com.aegon.SpringBootStudentCrud.Entities.StudentIdentity;
import com.aegon.SpringBootStudentCrud.RepFunction.RepFunction;
import com.aegon.SpringBootStudentCrud.Repoitory.CustomRepoImp;
import com.aegon.SpringBootStudentCrud.Repoitory.StudentRepo;
import com.aegon.SpringBootStudentCrud.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.Parameter;
import java.util.List;
import java.util.*;
import java.util.Optional;

@Service
public class ServiceImp implements StudentService {

    @Autowired
    StudentRepo studentRepo;

    @Autowired
    CustomRepoImp customRepoImp;

    @Autowired
    RepFunction repFunction;


    @Override
    public String addStudent(StudentRequest studentRequest) {
        try {
            studentRepo.save(repFunction.convertDtoToEntity(studentRequest));
        } catch (Exception e) {
            throw new InternalServerError("Data Failed to save!!!");
        }
        return "Success";
    }

    @Override
    public List<StudentResponse> fetchAllStudent() {
        List<Student> students = studentRepo.findAll();
        List<StudentResponse> studentResponses=students.stream()
                .map(student -> repFunction.convertEntityToDto(student))
                .toList();
        return studentResponses;
    }

    @Override
    public StudentResponse fetchStudentById(int regId, int rollNo) {
        Optional<Student> student = studentRepo.findById(new StudentIdentity(regId,rollNo));
        if(student.isEmpty()) return null;
        return repFunction.convertEntityToDto(student.get());
    }

    @Override
    public StudentResponse fetchStudentByRollNo(int rollNo) {
        Optional<Student> student = studentRepo.findByRollNo(rollNo);
        if(student.isEmpty()) return null;
        return repFunction.convertEntityToDto(student.get());
    }

    @Override
    public void deleteStudentById(int regId, int rollNo) {
        studentRepo.deleteById(new StudentIdentity(regId,rollNo));
    }

    @Override
    public void deleteStudentByRollNo(int rollNo) {
        studentRepo.deleteByRollNo(rollNo);
    }

    @Override
    public void deleteStudentAll() {
        studentRepo.deleteAll();
    }

    @Override
    public StudentResponse findByFullName(String firstName, String lastName) {
        Student student= studentRepo.findByFullName(firstName,lastName);
        return repFunction.convertEntityToDto(student);
    }

    @Override
    public StudentResponse findByFirstNameAndRollNo(String firstName, int rollNo) {
        Student student=  studentRepo.findByFirstNameAndRollNo(firstName,rollNo);
        return repFunction.convertEntityToDto(student);
    }

    @Override
    public void updateStudent(Object... fields) throws NoSuchFieldException, IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        Parameter[] parameters= (Parameter[]) fields[fields.length-1];
        System.out.println(parameters.length);
        for (int i = 0; i < fields.length-1; i++) {
            map.put(parameters[i].getName(),fields[i]);
        }
        customRepoImp.updateStudent(map);
    }
}
