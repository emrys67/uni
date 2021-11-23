package cz.mendel.uni.services;

import cz.mendel.uni.entities.Vacation;
import cz.mendel.uni.repositories.VacationRepository;
import cz.mendel.uni.services.exceptions.ServiceException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@AllArgsConstructor
public class VacationService {
    private VacationRepository vacationRepository;

    public Vacation findById(long id) {
        log.debug("Start service for getting vacation id {}", id);
        return vacationRepository.findById(id).orElseThrow(() -> {
            String msg = String.format("Vacation with Id [%s] doesn't exist", id);
            log.warn(msg);
            throw new ServiceException(msg);
        });
    }

    public Vacation save(Vacation vacation) {
        log.debug("Start service for saving vacation");
        if (vacation == null) {
            String msg = "Vacation can't be null";
            log.warn(msg);
            throw new ServiceException(msg);
        }
        return vacationRepository.save(vacation);
    }

    public List<Vacation> findAll() {
        log.debug("Start service for getting all vacations");
        return vacationRepository.findAll();
    }

    public void deleteById(long id) {
        log.debug("Start service for deleting vacation id {}", id);
        vacationRepository.deleteById(id);
    }

    public void update(Vacation vacation) {
        log.debug("Start service for updating vacation");
        if (vacation == null) {
            String msg = "Vacation can't be null";
            log.warn(msg);
            throw new ServiceException(msg);
        }
        vacationRepository.save(vacation);
    }
}
