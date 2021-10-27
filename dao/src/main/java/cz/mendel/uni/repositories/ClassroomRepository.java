package cz.mendel.uni.repositories;

import cz.mendel.uni.entities.Classroom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface ClassroomRepository extends CrudRepository<Classroom, Long> {
}
