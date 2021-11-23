package cz.mendel.uni.repositories;

import cz.mendel.uni.entities.*;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LectureRepository extends CrudRepository<Lecture, Long> {
    @Override
    List<Lecture> findAll();
//
//    @Transactional
//    @Modifying
//    @Query("update Lecture c set c.subject = ?1, c.classroom = ?2, c.timePeriod = ?3, c.teacher = ?4 where c.id = ?5")
//    void update(Subject subject, Classroom classroom, TimePeriod timePeriod, Teacher teacher, long id);
}
