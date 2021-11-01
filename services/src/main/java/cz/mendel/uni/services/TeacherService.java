package cz.mendel.uni.services;

import cz.mendel.uni.entities.Teacher;
import cz.mendel.uni.repositories.TeacherRepository;
import cz.mendel.uni.services.exceptions.ServiceException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@AllArgsConstructor
public class TeacherService {
    private static final Logger logger = LoggerFactory.getLogger(TeacherService.class.getName());
    private TeacherRepository teacherRepository;

    public Teacher findById(long id) {
        logger.debug("Start service for getting teacher id {}", id);
        return teacherRepository.findById(id).get();
    }

    public Teacher save(Teacher teacher) {
        logger.debug("Start service for saving teacher");
        if (teacher == null) {
            String msg = "Teacher can't be null";
            logger.warn(msg);
            throw new ServiceException(msg);
        }
        return teacherRepository.save(teacher);
    }

    public List<Teacher> findAll() {
        logger.debug("Start service for getting all teachers");
        return teacherRepository.findAll();
    }

    public void deleteById(long id) {
        logger.debug("Start service for deleting teacher id {}", id);
        teacherRepository.deleteById(id);
    }

    public void update(Teacher teacher) {
        logger.debug("Start service for updating teacher");
        if (teacher == null) {
            String msg = "Teacher can't be null";
            logger.warn(msg);
            throw new ServiceException(msg);
        }
        teacherRepository.update(teacher.getFirstname(), teacher.getLastname(), teacher.getGender(), teacher.getDateOfBirth(), teacher.getWorkingHours(),
                teacher.getVacation(), teacher.getId());
    }
}
