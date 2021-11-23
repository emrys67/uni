package cz.mendel.uni.services;

import cz.mendel.uni.entities.Classroom;
import cz.mendel.uni.repositories.ClassroomRepository;
import cz.mendel.uni.services.exceptions.ServiceException;
import lombok.AllArgsConstructor;
import lombok.NonNull;
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
            throw new ServiceException(msg);
        });
    }

    @NonNull
    public Classroom save(Classroom classroom) {
        log.debug("Start service for saving classroom");
        return classroomRepository.save(classroom);
    }

    @NonNull
    public void update(Classroom classroom) {
        log.debug("Start service for updating classroom");
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
