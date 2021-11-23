package cz.mendel.uni.repositories;

import cz.mendel.uni.entities.Gender;
import cz.mendel.uni.entities.Teacher;
import cz.mendel.uni.entities.TimePeriod;
import cz.mendel.uni.entities.Vacation;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

public interface TeacherRepository extends CrudRepository<Teacher, Long> {
    @Override
    List<Teacher> findAll();
//
//    @Transactional
//    @Modifying
//    @Query("update Teacher c set c.firstname = ?1, c.lastname = ?2, c.gender = ?3, c.dateOfBirth = ?4, c.workingHours = ?5, c.vacation = ?6 where c.id = ?7")
//    void update(String firstname, String lastname, Gender gender, Date dateOfBirth, TimePeriod workingHours, Vacation vacation, long id);
}
