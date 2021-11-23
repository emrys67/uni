package cz.mendel.uni.services;

import cz.mendel.uni.entities.Teacher;
import cz.mendel.uni.repositories.TeacherRepository;
import cz.mendel.uni.services.exceptions.ServiceException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@AllArgsConstructor
public class TeacherService {
    private TeacherRepository teacherRepository;

    public Teacher findById(long id) {
        log.debug("Start service for getting teacher id {}", id);
        return teacherRepository.findById(id).orElseThrow(() -> {
            String msg = String.format("Teacher with Id [%s] doesn't exist", id);
            log.warn(msg);
            throw new ServiceException(msg);
        });
    }

    public Teacher save(Teacher teacher) {
        log.debug("Start service for saving teacher");
        if (teacher == null) {
            String msg = "Teacher can't be null";
            log.warn(msg);
            throw new ServiceException(msg);
        }
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

    public void update(Teacher teacher) {
        log.debug("Start service for updating teacher");
        if (teacher == null) {
            String msg = "Teacher can't be null";
            log.warn(msg);
            throw new ServiceException(msg);
        }
        teacherRepository.save(teacher);
    }
}
