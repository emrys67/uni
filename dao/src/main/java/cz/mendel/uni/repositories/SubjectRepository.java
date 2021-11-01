package cz.mendel.uni.repositories;

import cz.mendel.uni.entities.Subject;
import cz.mendel.uni.entities.Teacher;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SubjectRepository extends CrudRepository<Subject, Long> {
    @Override
    List<Subject> findAll();

    @Transactional
    @Modifying
    @Query("update Subject c set c.name = ?1, c.description = ?2, c.supervisor = ?3 where c.id = ?4")
    void update(String name, String description, Teacher supervisor, long id);
}
