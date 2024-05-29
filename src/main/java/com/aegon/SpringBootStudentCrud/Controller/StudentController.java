package com.aegon.SpringBootStudentCrud.Controller;


import com.aegon.SpringBootStudentCrud.DTO.StudentDTO;
import com.aegon.SpringBootStudentCrud.Entities.Student;
import com.aegon.SpringBootStudentCrud.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/add/student")
    public ResponseEntity<String> addStudent(@RequestBody Student student){
        try {
            studentService.addStudent(student);
            //System.out.println("Testing......");
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        catch (Exception exception){
            exception.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/fetch/student")
    public ResponseEntity<List<StudentDTO>> fetchAllStudent(){
        List<StudentDTO> students= studentService.fetchAllStudent();
        if(students.size()==0) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.of(Optional.of(students));
    }

    @GetMapping("/fetch/student/{rollNo}")
    public ResponseEntity<StudentDTO> fetchStudent(@PathVariable int rollNo){
        StudentDTO student = studentService.fetchStudent(rollNo);
        if(student==null) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.of(Optional.of(student));
    }

    @DeleteMapping("/delete/{rollNo}")
    public ResponseEntity<String> deleteStudentByRollNo(@PathVariable int rollNo){
       String string= studentService.deleteStudentByRollNo(rollNo);
       if(string==null) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
       return ResponseEntity.ok(string);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteStudentAll(){
        studentService.deleteStudentAll();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/fullname/{fullName}")
    public ResponseEntity<StudentDTO> findByFullName(@PathVariable String fullName){
        String[] str=fullName.split(" ");
        if(str.length<=1) return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        //System.out.println("Testing......");
        StudentDTO student=studentService.findByFullName(str[0],str[1]);
        if(student==null) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.of(Optional.of(student));
    }

    @GetMapping("/firstnameandroll/{fNameAndRoll}")
    public ResponseEntity<StudentDTO>  findByFirstNameAndRollNo(@PathVariable String fNameAndRoll){
        String[] str=fNameAndRoll.split(" ");
        if(str.length<=1) return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        StudentDTO student=studentService.findByFirstNameAndRollNo(str[0],Integer.parseInt(str[1]));
        if(student==null) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        //System.out.println("Testing......");
        return ResponseEntity.of(Optional.of(student));
    }
}
