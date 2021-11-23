package cz.mendel.uni.services;

import cz.mendel.uni.entities.Vacation;
import cz.mendel.uni.repositories.VacationRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@AllArgsConstructor
public class VacationService {
    private VacationRepository vacationRepository;

    public Vacation findById(long id) {
        log.debug("Start service for getting vacation id {}", id);
        return vacationRepository.findById(id).orElse(null);
    }

    @NonNull
    public Vacation save(Vacation vacation) {
        log.debug("Start service for saving vacation");
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

    @NonNull
    public void update(Vacation vacation) {
        log.debug("Start service for updating vacation");
        vacationRepository.save(vacation);
    }
}
