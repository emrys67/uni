package cz.mendel.uni.repositories;

import cz.mendel.uni.entities.TimePeriod;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TimePeriodRepository extends CrudRepository<TimePeriod, Long> {
    @Override
    List<TimePeriod> findAll();
}
