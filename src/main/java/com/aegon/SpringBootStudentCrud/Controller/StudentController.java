package com.aegon.SpringBootStudentCrud.Controller;


import com.aegon.SpringBootStudentCrud.DTO.StudentRequest;
import com.aegon.SpringBootStudentCrud.DTO.StudentResponse;
import com.aegon.SpringBootStudentCrud.RepFunction.RepFunction;
import com.aegon.SpringBootStudentCrud.ServiceImp.ServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    @Autowired
    ServiceImp serviceImp;

    @Autowired
    RepFunction repFunction;

    @PostMapping("/add/student")
    public ResponseEntity<String> addStudent(@RequestBody StudentRequest studentRequest){
        String string=serviceImp.addStudent(studentRequest);
        if(string.equalsIgnoreCase("Success")) {
            return repFunction.created();
        }
        else {
            return repFunction.serverError();
        }
    }

    @GetMapping("/fetch/student")
    public ResponseEntity<List<StudentResponse>> fetchAllStudent(){
        List<StudentResponse> studentResponses= serviceImp.fetchAllStudent();
        if(studentResponses.size()==0) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        return ResponseEntity.of(Optional.of(studentResponses));
    }

    @GetMapping("/fetch/student/{regNo}-{rollNo}")
    public ResponseEntity<StudentResponse> fetchStudent(@PathVariable int regNo,@PathVariable int rollNo){
        StudentResponse studentResponse = serviceImp.fetchStudentById(regNo,rollNo);
        if(studentResponse==null) return repFunction.noContent();
        else {
            return ResponseEntity.of(Optional.of(studentResponse));
        }
    }

    @GetMapping("/fetch/student/{rollNo}")
    public ResponseEntity<StudentResponse> fetchStudentByRoll(@PathVariable int rollNo){
        StudentResponse studentResponse = serviceImp.fetchStudentByRollNo(rollNo);
        if(studentResponse==null) return repFunction.noContent();
        else {
            return ResponseEntity.of(Optional.of(studentResponse));
        }
    }


    @DeleteMapping("/delete/{regNo}-{rollNo}")
    public ResponseEntity<String> deleteStudentById(@PathVariable int regNo, @PathVariable int rollNo){
        serviceImp.deleteStudentById(regNo,rollNo);
        return repFunction.success();
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
    public ResponseEntity<StudentResponse> findByFullName(@PathVariable String fullName){
        String[] str=fullName.split(" ");
        if(str.length<=1) return repFunction.notAcceptable();
        StudentResponse studentResponse=serviceImp.findByFullName(str[0],str[1]);
        if(studentResponse==null) return repFunction.noContent();
        return ResponseEntity.of(Optional.of(studentResponse));
    }

    @GetMapping("/firstnameandroll/{fNameAndRoll}")
    public ResponseEntity<StudentResponse>  findByFirstNameAndRollNo(@PathVariable String fNameAndRoll){
        String[] str=fNameAndRoll.split(" ");
        if(str.length<=1) return repFunction.notAcceptable();
        StudentResponse studentResponse=serviceImp.findByFirstNameAndRollNo(str[0],Integer.parseInt(str[1]));
        if(studentResponse==null) return repFunction.noContent();
        return ResponseEntity.of(Optional.of(studentResponse));
    }
    @PutMapping("/update/{firstName}/{lastName}/{dob}")
    public void updateStudent(@PathVariable String firstName,@PathVariable String lastName,@PathVariable String dob) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException {
        Method method = StudentController.class.getMethod("updateStudent", String.class, String.class,String.class);
        Parameter[] parameters = method.getParameters();
        serviceImp.updateStudent(firstName,lastName,dob,parameters);
    }
}
