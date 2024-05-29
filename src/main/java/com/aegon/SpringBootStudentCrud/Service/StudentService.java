package com.aegon.SpringBootStudentCrud.Service;

import com.aegon.SpringBootStudentCrud.DTO.StudentDTO;
import com.aegon.SpringBootStudentCrud.Entities.Student;
import com.aegon.SpringBootStudentCrud.Repoitory.StudentRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    public StudentRepo studentRepo;

    @Autowired
    ModelMapper modelMapper;

    public StudentDTO convertEntityToDto(Student student){
        StudentDTO studentDTO=new StudentDTO();
        studentDTO=modelMapper.map(student,StudentDTO.class);
        return studentDTO;
    }


    public StudentDTO addStudent(Student student){
        StudentDTO studentDTO = convertEntityToDto(studentRepo.save(student));
        //System.out.println(save);
        return studentDTO;
    }

    public List<StudentDTO> fetchAllStudent(){
        return studentRepo.findAll().stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }
    public StudentDTO fetchStudent(int rollNo) {
        Optional<StudentDTO> student = studentRepo.findById(rollNo).stream().map(this::convertEntityToDto).findFirst();
        //System.out.println("Testing......");
        if(student.isEmpty()) return null;
        return student.get();
    }
    public String deleteStudentByRollNo(int rollNo) {
        List<StudentDTO> students=studentRepo.findAll().stream().map(this::convertEntityToDto).collect(Collectors.toList());
        Optional<StudentDTO> first = students.stream().filter(stu -> stu.getRollNo() == rollNo).findFirst();
        if(first.isEmpty()) return null;
//        System.out.println("Testing......");
        studentRepo.deleteById(rollNo);
        return "Deleted";
    }

    public String deleteStudentAll() {
        studentRepo.deleteAll();
        return "Deleted";
    }

    public StudentDTO findByFullName(String firstName, String lastName) {
        return convertEntityToDto(studentRepo.findByFullName(firstName,lastName));
    }

    public StudentDTO findByFirstNameAndRollNo(String firstName, int rollNo) {
        return convertEntityToDto(studentRepo.findByFirstNameAndRollNo(firstName,rollNo));
    }

}
