package cz.mendel.uni.repositories;

import cz.mendel.uni.entities.Vacation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacationRepository extends CrudRepository<Vacation, Long> {
}
