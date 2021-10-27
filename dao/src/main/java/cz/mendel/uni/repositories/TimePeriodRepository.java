package cz.mendel.uni.repositories;

import cz.mendel.uni.entities.TimePeriod;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimePeriodRepository extends CrudRepository<TimePeriod, Long> {
}
