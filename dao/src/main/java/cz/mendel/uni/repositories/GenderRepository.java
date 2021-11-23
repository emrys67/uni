package cz.mendel.uni.repositories;

import cz.mendel.uni.entities.Gender;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GenderRepository extends CrudRepository<Gender, Long> {
    @Override
    List<Gender> findAll();
}
