package cz.mendel.uni.services;

import cz.mendel.uni.entities.Teacher;
import cz.mendel.uni.repositories.TeacherRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@AllArgsConstructor
public class TeacherService {
    private TeacherRepository teacherRepository;

    public Teacher findById(long id) {
        log.debug("Start service for getting teacher id {}", id);
        return teacherRepository.findById(id).orElse(null);
    }

    public Teacher save(@NonNull Teacher teacher) {
        log.debug("Start service for saving teacher");
        return teacherRepository.save(teacher);
    }

    public List<Teacher> findAll() {
        log.debug("Start service for getting all teachers");
        return teacherRepository.findAll();
    }

    public void deleteById(long id) {
        log.debug("Start service for deleting teacher id {}", id);
        teacherRepository.deleteById(id);
    }

    public void update(@NonNull Teacher teacher) {
        log.debug("Start service for updating teacher");
        teacherRepository.save(teacher);
    }
}
