package cz.mendel.uni.repositories;

import cz.mendel.uni.entities.TimePeriod;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalTime;
import java.util.List;

public interface TimePeriodRepository extends CrudRepository<TimePeriod, Long> {
    @Override
    List<TimePeriod> findAll();
//
//    @Transactional
//    @Modifying
//    @Query("update TimePeriod c set c.startDate = ?1, c.startTime = ?2, c.endTime = ?3, c.endDate = ?4 where c.id = ?5")
//    void update(Date startDate, LocalTime startTime, LocalTime endTime, Date endDate, long id);
}
