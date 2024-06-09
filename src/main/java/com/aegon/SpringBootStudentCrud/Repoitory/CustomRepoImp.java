package com.aegon.SpringBootStudentCrud.Repoitory;

import com.aegon.SpringBootStudentCrud.Entities.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class CustomRepoImp implements CustomRepo{

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public void updateStudent(Map<String ,Object> fields) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaUpdate<Student> update = cb.createCriteriaUpdate(Student.class);
        Root<Student> root = update.from(Student.class);
        fields.forEach((field, value) -> {
            update.set(root.get(field), value);
        });

        update.where(cb.equal(root.get("dob"), fields.get("dob")));

        entityManager.createQuery(update).executeUpdate();
    }
}
