package cz.mendel.uni.services;

import cz.mendel.uni.entities.Student;
import cz.mendel.uni.repositories.StudentRepository;
import cz.mendel.uni.services.exceptions.ServiceException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static java.lang.String.format;

@AllArgsConstructor
public class StudentService {
    private static final Logger logger = LoggerFactory.getLogger(StudentService.class.getName());
    private StudentRepository studentRepository;

    public Student findById(long id) {
        logger.debug("Start service for getting student id {}", id);
        return studentRepository.findById(id).orElseThrow(() -> {
            String msg = format("Student with Id [%s] doesn't exist", id);
            logger.warn(msg);
            throw new ServiceException(msg);
        });
    }

    public Student save(Student student) {
        logger.debug("Start service for saving student");
        if (student == null) {
            String msg = "Student can't be null";
            logger.warn(msg);
            throw new ServiceException(msg);
        }
        return studentRepository.save(student);
    }

    public List<Student> findAll() {
        logger.debug("Start service for getting all students");
        return studentRepository.findAll();
    }

    public void deleteById(long id) {
        logger.debug("Start service for deleting student id {}", id);
        studentRepository.deleteById(id);
    }

    public void update(Student student) {
        logger.debug("Start service for updating student");
        if (student == null) {
            String msg = "Student can't be null";
            logger.warn(msg);
            throw new ServiceException(msg);
        }
        studentRepository.update(student.getFirstname(), student.getLastname(), student.getGender(),
                student.getDateOfBirth(), student.getStudyYear(), student.getGroup(), student.getId());
    }
}
