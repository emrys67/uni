package cz.mendel.uni.repositories;

import cz.mendel.uni.entities.Subject;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SubjectRepository extends CrudRepository<Subject, Long> {
    @Override
    List<Subject> findAll();
}
