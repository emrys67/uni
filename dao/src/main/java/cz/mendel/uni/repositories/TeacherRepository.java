package cz.mendel.uni.repositories;

import cz.mendel.uni.entities.Teacher;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TeacherRepository extends CrudRepository<Teacher, Long> {
    @Override
    List<Teacher> findAll();
    @Transactional
    @Modifying
    @Query("update Teacher c set c.firstname = ?1, c.lastname = ?2, c.gender = ?3, c.dateOfBirth = ?4, c.workingHours = ?5, c.vacation = ?6 where c.id = ?7")
    void update(String firstname, String lastname, String g, long id);
}
