package cz.mendel.uni.services;

import cz.mendel.uni.entities.TimePeriod;
import cz.mendel.uni.repositories.TimePeriodRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@AllArgsConstructor
public class TimePeriodService {
    private TimePeriodRepository timePeriodRepository;

    public TimePeriod findById(long id) {
        log.debug("Start service for getting timeperiod id {}", id);
        return timePeriodRepository.findById(id).orElse(null);
    }

    public TimePeriod save(@NonNull TimePeriod timePeriod) {
        log.debug("Start service for saving timeperiod");
        return timePeriodRepository.save(timePeriod);
    }

    public List<TimePeriod> findAll() {
        log.debug("Start service for getting all timeperiods");
        return timePeriodRepository.findAll();
    }

    public void deleteById(long id) {
        log.debug("Start service for deleting timeperiod id {}", id);
        timePeriodRepository.deleteById(id);
    }

    public void update(@NonNull TimePeriod timePeriod) {
        log.debug("Start service for updating timeperiod");
        timePeriodRepository.save(timePeriod);
    }
}
