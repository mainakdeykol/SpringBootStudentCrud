package com.aegon.SpringBootStudentCrud.Controller;


import com.aegon.SpringBootStudentCrud.DTO.StudentDTO;
import com.aegon.SpringBootStudentCrud.Entities.Student;
import com.aegon.SpringBootStudentCrud.RepFunction.RepFunction;
import com.aegon.SpringBootStudentCrud.ServiceImp.ServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class StudentController {

    @Autowired
    ServiceImp serviceImp;

    @Autowired
    RepFunction repFunction;

    @PostMapping("/add/student")
    public ResponseEntity<String> addStudent(@RequestBody Student student){
        String string=serviceImp.addStudent(student);
        if(string.equalsIgnoreCase("Success")) {
            return repFunction.created();
        }
        else {
            return repFunction.serverError();
        }
    }

    @GetMapping("/fetch/student")
    public ResponseEntity<List<StudentDTO>> fetchAllStudent(){
        List<StudentDTO> students= serviceImp.fetchAllStudent().stream()
                .map(student -> repFunction.convertEntityToDto(student))
                .collect(Collectors.toList());
        if(students.size()==0) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.of(Optional.of(students));
    }

    @GetMapping("/fetch/student/{rollNo}")
    public ResponseEntity<StudentDTO> fetchStudent(@PathVariable int rollNo){
        Student student = serviceImp.fetchStudent(rollNo);
        if(student==null) return repFunction.noContent();
        else {
            return ResponseEntity.of(Optional.of(repFunction.convertEntityToDto(student)));
        }
    }

    @DeleteMapping("/delete/{rollNo}")
    public ResponseEntity<String> deleteStudentByRollNo(@PathVariable int rollNo){
        serviceImp.deleteStudentByRollNo(rollNo);
        return repFunction.success();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteStudentAll(){
        serviceImp.deleteStudentAll();
        return repFunction.success();
    }

    @GetMapping("/fullname/{fullName}")
    public ResponseEntity<StudentDTO> findByFullName(@PathVariable String fullName){
        String[] str=fullName.split(" ");
        if(str.length<=1) return repFunction.notAcceptable();
        Student student=serviceImp.findByFullName(str[0],str[1]);
        if(student==null) return repFunction.noContent();
        return ResponseEntity.of(Optional.of(repFunction.convertEntityToDto(student)));
    }

    @GetMapping("/firstnameandroll/{fNameAndRoll}")
    public ResponseEntity<StudentDTO>  findByFirstNameAndRollNo(@PathVariable String fNameAndRoll){
        String[] str=fNameAndRoll.split(" ");
        if(str.length<=1) return repFunction.notAcceptable();
        Student student=serviceImp.findByFirstNameAndRollNo(str[0],Integer.parseInt(str[1]));
        if(student==null) return repFunction.noContent();
        return ResponseEntity.of(Optional.of(repFunction.convertEntityToDto(student)));
    }
}
