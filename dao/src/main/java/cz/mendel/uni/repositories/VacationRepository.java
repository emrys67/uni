package cz.mendel.uni.repositories;

import cz.mendel.uni.entities.Vacation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VacationRepository extends CrudRepository<Vacation, Long> {
    @Override
    List<Vacation> findAll();
}
