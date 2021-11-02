package cz.mendel.uni.services;

import cz.mendel.uni.entities.Vacation;
import cz.mendel.uni.repositories.VacationRepository;
import cz.mendel.uni.services.exceptions.ServiceException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static java.lang.String.format;

@AllArgsConstructor
public class VacationService {
    private static final Logger logger = LoggerFactory.getLogger(VacationService.class.getName());
    private VacationRepository vacationRepository;

    public Vacation findById(long id) {
        logger.debug("Start service for getting vacation id {}", id);
        return vacationRepository.findById(id).orElseThrow(() -> {
            String msg = format("Vacation with Id [%s] doesn't exist", id);
            logger.warn(msg);
            throw new ServiceException(msg);
        });
    }

    public Vacation save(Vacation vacation) {
        logger.debug("Start service for saving vacation");
        if (vacation == null) {
            String msg = "Vacation can't be null";
            logger.warn(msg);
            throw new ServiceException(msg);
        }
        return vacationRepository.save(vacation);
    }

    public List<Vacation> findAll() {
        logger.debug("Start service for getting all vacations");
        return vacationRepository.findAll();
    }

    public void deleteById(long id) {
        logger.debug("Start service for deleting vacation id {}", id);
        vacationRepository.deleteById(id);
    }

    public void update(Vacation vacation) {
        logger.debug("Start service for updating vacation");
        if (vacation == null) {
            String msg = "Vacation can't be null";
            logger.warn(msg);
            throw new ServiceException(msg);
        }
        vacationRepository.update(vacation.getDescription(), vacation.getTimePeriod(), vacation.getId());
    }
}
