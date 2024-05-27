package com.aegon.SpringBootStudentCrud.Service;

import com.aegon.SpringBootStudentCrud.Entities.Student;
import com.aegon.SpringBootStudentCrud.Repoitory.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    public StudentRepo studentRepo;

    public Student addStudent(Student student){
        Student save = studentRepo.save(student);
        //System.out.println(save);
        return save;
    }

    public List<Student> fetchAllStudent(){
        return studentRepo.findAll();
    }
    public Student fetchStudent(int rollNo) {
        Optional<Student> student = studentRepo.findById(rollNo);
        //System.out.println("Testing......");
        if(student.isEmpty()) return null;
        return student.get();
    }
    public String deleteStudentByRollNo(int rollNo) {
        List<Student> students=studentRepo.findAll();
        Optional<Student> first = students.stream().filter(stu -> stu.getRollNo() == rollNo).findFirst();
        if(first.isEmpty()) return null;
//        System.out.println("Testing......");
        studentRepo.deleteById(rollNo);
        return "Deleted";
    }

    public String deleteStudentAll() {
        studentRepo.deleteAll();
        return "Deleted";
    }

    public Student findByFullName(String firstName, String lastName) {
        return studentRepo.findByFullName(firstName,lastName);
    }

    public Student findByFirstNameAndRollNo(String firstName, int rollNo) {
        return studentRepo.findByFirstNameAndRollNo(firstName,rollNo);
    }




//    public String getStudentByFirstName(String firstName) {
//        return "Please enter valid name...";
//    }
//    public String getStudentByLastName(String firstName) {
//        return "Please enter valid name...";
//    }
}
