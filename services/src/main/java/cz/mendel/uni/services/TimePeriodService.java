package cz.mendel.uni.services;

import cz.mendel.uni.entities.TimePeriod;
import cz.mendel.uni.repositories.TimePeriodRepository;
import cz.mendel.uni.services.exceptions.ServiceException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@AllArgsConstructor
public class TimePeriodService {
    private static final Logger logger = LoggerFactory.getLogger(TimePeriodService.class.getName());
    private TimePeriodRepository timePeriodRepository;

    public TimePeriod findById(long id) {
        logger.debug("Start service for getting timeperiod id {}", id);
        return timePeriodRepository.findById(id).get();
    }

    public TimePeriod save(TimePeriod timePeriod) {
        logger.debug("Start service for saving timeperiod");
        if (timePeriod == null) {
            String msg = "TimePeriod can't be null";
            logger.warn(msg);
            throw new ServiceException(msg);
        }
        return timePeriodRepository.save(timePeriod);
    }

    public List<TimePeriod> findAll() {
        logger.debug("Start service for getting all timeperiods");
        return timePeriodRepository.findAll();
    }

    public void deleteById(long id) {
        logger.debug("Start service for deleting timeperiod id {}", id);
        timePeriodRepository.deleteById(id);
    }

    public void update(TimePeriod timePeriod) {
        logger.debug("Start service for updating timeperiod");
        if (timePeriod == null) {
            String msg = "TimePeriod can't be null";
            logger.warn(msg);
            throw new ServiceException(msg);
        }
        timePeriodRepository.update(timePeriod.getStartDate(), timePeriod.getStartTime(), timePeriod.getEndTime(),
                timePeriod.getEndDate(), timePeriod.getId());
    }
}
