package com.aegon.SpringBootStudentCrud.Repoitory;

import com.aegon.SpringBootStudentCrud.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<Student,Integer> {
    @Query("Select stu from Student stu where stu.firstName= :fName and stu.lastName= :lName")
    public Student findByFullName(@Param("fName") String firstName, @Param("lName") String lastName);

    @Query("Select stu from Student stu where stu.firstName= :fName and stu.rollNo= :rno")
    public Student findByFirstNameAndRollNo(@Param("fName") String firstName, @Param("rno") int lastName);
}
