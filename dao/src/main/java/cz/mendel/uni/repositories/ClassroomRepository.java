package cz.mendel.uni.repositories;

import cz.mendel.uni.entities.Classroom;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ClassroomRepository extends CrudRepository<Classroom, Long> {
    @Override
    List<Classroom> findAll();
//
//    @Transactional
//    @Modifying
//    @Query("update Classroom c set c.capacity = ?1 where c.id = ?2")
//    void update(int capacity, long id);
}
