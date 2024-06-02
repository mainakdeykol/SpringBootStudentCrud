package com.aegon.SpringBootStudentCrud.RepFunction;

import com.aegon.SpringBootStudentCrud.DTO.StudentDTO;
import com.aegon.SpringBootStudentCrud.Entities.Student;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RepFunction {
    @Autowired
    ModelMapper modelMapper;
    public StudentDTO convertEntityToDto(Student student){
        return modelMapper.map(student,StudentDTO.class);
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

    public ResponseEntity<StudentDTO> notAcceptable(){
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }

    public ResponseEntity<StudentDTO> noContent(){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
