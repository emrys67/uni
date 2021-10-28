package cz.mendel.uni.repositories;

import cz.mendel.uni.entities.Subject;
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
    @Query("update Classroom c set c.capacity = ?1 where c.id = ?2")
    void update(int capacity, long id);
}
