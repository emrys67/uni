package cz.mendel.uni.repositories;

import cz.mendel.uni.entities.Student;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentRepository extends CrudRepository<Student, Long> {
    @Override
    List<Student> findAll();
}
