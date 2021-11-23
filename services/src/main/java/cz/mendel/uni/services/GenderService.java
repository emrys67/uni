package cz.mendel.uni.services;

import cz.mendel.uni.entities.Gender;
import cz.mendel.uni.repositories.GenderRepository;
import cz.mendel.uni.services.exceptions.ServiceException;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@AllArgsConstructor
public class GenderService {
    private GenderRepository genderRepository;

    public Gender findById(long id) {
        log.debug("Start service for getting gender id {}", id);
        return genderRepository.findById(id).orElseThrow(() -> {
            String msg = String.format("Gender with Id [%s] doesn't exist", id);
            throw new ServiceException(msg);
        });
    }

    @NonNull
    public Gender save(Gender gender) {
        log.debug("Start service for saving gender");
        return genderRepository.save(gender);
    }

    public List<Gender> findAll() {
        log.debug("Start service for getting all genders");
        return genderRepository.findAll();
    }

    public void deleteById(long id) {
        log.debug("Start service for deleting gender id {}", id);
        genderRepository.deleteById(id);
    }

    @NonNull
    public void update(Gender gender) {
        log.debug("Start service for updating gender");
        genderRepository.save(gender);
    }
}
