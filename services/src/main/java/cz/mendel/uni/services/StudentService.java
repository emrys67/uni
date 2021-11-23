package cz.mendel.uni.services;

import cz.mendel.uni.entities.Student;
import cz.mendel.uni.repositories.StudentRepository;
import cz.mendel.uni.services.exceptions.ServiceException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@AllArgsConstructor
public class StudentService {
    private StudentRepository studentRepository;

    public Student findById(long id) {
        log.debug("Start service for getting student id {}", id);
        return studentRepository.findById(id).orElseThrow(() -> {
            String msg = String.format("Student with Id [%s] doesn't exist", id);
            log.warn(msg);
            throw new ServiceException(msg);
        });
    }

    public Student save(Student student) {
        log.debug("Start service for saving student");
        if (student == null) {
            String msg = "Student can't be null";
            log.warn(msg);
            throw new ServiceException(msg);
        }
        return studentRepository.save(student);
    }

    public List<Student> findAll() {
        log.debug("Start service for getting all students");
        return studentRepository.findAll();
    }

    public void deleteById(long id) {
        log.debug("Start service for deleting student id {}", id);
        studentRepository.deleteById(id);
    }

    public void update(Student student) {
        log.debug("Start service for updating student");
        if (student == null) {
            String msg = "Student can't be null";
            log.warn(msg);
            throw new ServiceException(msg);
        }
        studentRepository.save(student);
    }
}
