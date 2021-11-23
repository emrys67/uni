package cz.mendel.uni.repositories;

import cz.mendel.uni.entities.Teacher;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TeacherRepository extends CrudRepository<Teacher, Long> {
    @Override
    List<Teacher> findAll();
}
