package com.aegon.SpringBootStudentCrud.Repoitory;

import com.aegon.SpringBootStudentCrud.Entities.Student;
import com.aegon.SpringBootStudentCrud.Entities.StudentIdentity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<Student, StudentIdentity> {
    @Query("Select stu from Student stu where stu.firstName= :fName and stu.lastName= :lName")
    public Student findByFullName(@Param("fName") String firstName, @Param("lName") String lastName);

    @Query("Select stu from Student stu where stu.firstName= :fName and stu.id.rollNo= :rno")
    public Student findByFirstNameAndRollNo(@Param("fName") String firstName, @Param("rno") int lastName);

    @Query("Select stu from Student stu where stu.id.rollNo= :rno")
    public Optional<Student> findByRollNo(@Param("rno") int rollNo);

    @Modifying
    @Transactional
    @Query("Delete from Student stu where stu.id.rollNo= :rno")
    void deleteByRollNo(@Param("rno") int rollNo);
}
