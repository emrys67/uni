package cz.mendel.uni.services;

import cz.mendel.uni.entities.Classroom;
import cz.mendel.uni.repositories.ClassroomRepository;
import cz.mendel.uni.services.exceptions.ServiceException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@AllArgsConstructor
public class ClassroomService {
    private ClassroomRepository classroomRepository;

    public Classroom findById(long id) {
        log.debug("Start service for getting classroom id {}", id);
        return classroomRepository.findById(id).orElseThrow(() -> {
            String msg = String.format("Classroom with Id [%s] doesn't exist", id);
            log.warn(msg);
            throw new ServiceException(msg);
        });
    }

    public Classroom save(Classroom classroom) {
        log.debug("Start service for saving classroom");
        if (classroom == null) {
            String msg = "Classroom can't be null";
            log.warn(msg);
            throw new ServiceException(msg);
        }
        return classroomRepository.save(classroom);
    }

    public void update(Classroom classroom) {
        log.debug("Start service for updating classroom");
        if (classroom == null) {
            String msg = "Classroom can't be null";
            log.warn(msg);
            throw new ServiceException(msg);
        }
        classroomRepository.save(classroom);
    }

    public List<Classroom> findAll() {
        log.debug("Start service for getting all classrooms");
        return classroomRepository.findAll();
    }

    public void deleteById(long id) {
        log.debug("Start service for deleting classroom id {}", id);
        classroomRepository.deleteById(id);
    }
}
