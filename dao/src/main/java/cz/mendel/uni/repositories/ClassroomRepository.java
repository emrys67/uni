package cz.mendel.uni.repositories;

import cz.mendel.uni.entities.Classroom;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClassroomRepository extends CrudRepository<Classroom, Long> {
    @Override
    List<Classroom> findAll();
}
