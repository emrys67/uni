package cz.mendel.uni.services;

import cz.mendel.uni.entities.Gender;
import cz.mendel.uni.repositories.GenderRepository;
import cz.mendel.uni.services.exceptions.ServiceException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static java.lang.String.format;

@AllArgsConstructor
public class GenderService {
    private static final Logger logger = LoggerFactory.getLogger(GenderService.class.getName());
    private GenderRepository genderRepository;

    public Gender findById(long id) {
        logger.debug("Start service for getting gender id {}", id);
        return genderRepository.findById(id).orElseThrow(() -> {
            String msg = format("Gender with Id [%s] doesn't exist", id);
            logger.warn(msg);
            throw new ServiceException(msg);
        });
    }

    public Gender save(Gender gender) {
        logger.debug("Start service for saving gender");
        if (gender == null) {
            String msg = "Gender can't be null";
            logger.warn(msg);
            throw new ServiceException(msg);
        }
        return genderRepository.save(gender);
    }

    public List<Gender> findAll() {
        logger.debug("Start service for getting all genders");
        return genderRepository.findAll();
    }

    public void deleteById(long id) {
        logger.debug("Start service for deleting gender id {}", id);
        genderRepository.deleteById(id);
    }

    public void update(Gender gender) {
        logger.debug("Start service for updating gender");
        if (gender == null) {
            String msg = "Gender can't be null";
            logger.warn(msg);
            throw new ServiceException(msg);
        }
        genderRepository.update(gender.getName(), gender.getId());
    }

}
