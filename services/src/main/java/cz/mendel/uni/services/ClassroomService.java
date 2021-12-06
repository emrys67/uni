package cz.mendel.uni.services;

import cz.mendel.uni.entities.Classroom;
import cz.mendel.uni.repositories.ClassroomRepository;
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
        return classroomRepository.findById(id).orElse(null);
    }

    public Classroom save(@NonNull Classroom classroom) {
        log.debug("Start service for saving classroom");
        return classroomRepository.save(classroom);
    }

    public void update(@NonNull Classroom classroom) {
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
