package cz.mendel.uni.repositories;

import cz.mendel.uni.entities.Gender;
import cz.mendel.uni.entities.Group;
import cz.mendel.uni.entities.Student;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

public interface StudentRepository extends CrudRepository<Student, Long> {
    @Override
    List<Student> findAll();
//
//    @Transactional
//    @Modifying
//    @Query("update Student c set c.firstname = ?1, c.lastname = ?2, c.gender = ?3, c.dateOfBirth = ?4, c.studyYear = ?5 where c.id = ?6")
//    void update(String firstname, String lastname, Gender gender, Date dateOfBirth, int studyYear, long id);
}
