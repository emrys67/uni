package cz.mendel.uni.services;

import cz.mendel.uni.entities.TimePeriod;
import cz.mendel.uni.repositories.TimePeriodRepository;
import cz.mendel.uni.services.exceptions.ServiceException;
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
        return timePeriodRepository.findById(id).orElseThrow(() -> {
            String msg = String.format("TimePeriod with Id [%s] doesn't exist", id);
            throw new ServiceException(msg);
        });
    }

    @NonNull
    public TimePeriod save(TimePeriod timePeriod) {
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

    @NonNull
    public void update(TimePeriod timePeriod) {
        log.debug("Start service for updating timeperiod");
        timePeriodRepository.save(timePeriod);
    }
}
