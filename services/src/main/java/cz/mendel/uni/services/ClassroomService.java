package cz.mendel.uni.services;

import cz.mendel.uni.entities.Classroom;
import cz.mendel.uni.repositories.ClassroomRepository;
import cz.mendel.uni.services.exceptions.ServiceException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static java.lang.String.format;

@AllArgsConstructor
public class ClassroomService {
    private static final Logger logger = LoggerFactory.getLogger(ClassroomService.class.getName());
    private ClassroomRepository classroomRepository;

    public Classroom findById(long id) {
        logger.debug("Start service for getting classroom id {}", id);
        return classroomRepository.findById(id).orElseThrow(() -> {
            String msg = format("Classroom with Id [%s] doesn't exist", id);
            logger.warn(msg);
            throw new ServiceException(msg);
        });
    }

    public Classroom save(Classroom classroom) {
        logger.debug("Start service for saving classroom");
        if (classroom == null) {
            String msg = "Classroom can't be null";
            logger.warn(msg);
            throw new ServiceException(msg);
        }
        return classroomRepository.save(classroom);
    }

    public void update(Classroom classroom) {
        logger.debug("Start service for updating classroom");
        if (classroom == null) {
            String msg = "Classroom can't be null";
            logger.warn(msg);
            throw new ServiceException(msg);
        }
        classroomRepository.update(classroom.getCapacity(), classroom.getId());
    }

    public List<Classroom> findAll() {
        logger.debug("Start service for getting all classrooms");
        return classroomRepository.findAll();
    }

    public void deleteById(long id) {
        logger.debug("Start service for deleting classroom id {}", id);
        classroomRepository.deleteById(id);
    }
}
