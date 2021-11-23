package cz.mendel.uni.repositories;

import cz.mendel.uni.entities.*;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LectureRepository extends CrudRepository<Lecture, Long> {
    @Override
    List<Lecture> findAll();
}
