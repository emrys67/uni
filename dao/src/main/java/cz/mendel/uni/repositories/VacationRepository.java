package cz.mendel.uni.repositories;

import cz.mendel.uni.entities.TimePeriod;
import cz.mendel.uni.entities.Vacation;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface VacationRepository extends CrudRepository<Vacation, Long> {
    @Override
    List<Vacation> findAll();

//    @Transactional
//    @Modifying
//    @Query("update Vacation c set c.description = ?1, c.timePeriod = ?2 where c.id = ?3")
//    void update(String description, TimePeriod timePeriod, long id);
}
