package com.aegon.SpringBootStudentCrud.RepFunction;

import com.aegon.SpringBootStudentCrud.DTO.StudentRequest;
import com.aegon.SpringBootStudentCrud.DTO.StudentResponse;
import com.aegon.SpringBootStudentCrud.Entities.Student;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class RepFunction {
    @Autowired
    ModelMapper modelMapper;
    public StudentResponse convertEntityToDto(Student student){
        return modelMapper.map(student,StudentResponse.class);
    }
    public Student convertDtoToEntity(StudentRequest studentRequest){
        return modelMapper.map(studentRequest,Student.class);
    }
    public ResponseEntity<String> created(){
        return new ResponseEntity<String>(HttpStatus.CREATED);
    }

    public ResponseEntity<String> success(){
        return new ResponseEntity<String>(HttpStatus.OK);
    }

    public ResponseEntity<String> serverError(){
        return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<StudentResponse> notAcceptable(){
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }

    public ResponseEntity<StudentResponse> noContent(){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
