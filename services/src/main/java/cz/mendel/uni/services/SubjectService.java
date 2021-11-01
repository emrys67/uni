package cz.mendel.uni.services;

import cz.mendel.uni.entities.Subject;
import cz.mendel.uni.entities.Teacher;
import cz.mendel.uni.repositories.SubjectRepository;
import cz.mendel.uni.services.exceptions.ServiceException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@AllArgsConstructor
public class SubjectService {
    private static final Logger logger = LoggerFactory.getLogger(SubjectService.class.getName());
    private SubjectRepository subjectRepository;

    public Subject findById(long id) {
        logger.debug("Start service for getting subject id {}", id);
        return subjectRepository.findById(id).get();
    }

    public Subject save(Subject subject) {
        logger.debug("Start service for saving subject");
        if (subject == null) {
            String msg = "Subject can't be null";
            logger.warn(msg);
            throw new ServiceException(msg);
        }
        return subjectRepository.save(subject);
    }

    public List<Subject> findAll() {
        logger.debug("Start service for getting all subjects");
        return subjectRepository.findAll();
    }

    public void deleteById(long id) {
        logger.debug("Start service for deleting subject id {}", id);
        subjectRepository.deleteById(id);
    }

    public void update(Subject subject) {
        logger.debug("Start service for updating subject");
        if (subject == null) {
            String msg = "Subject can't be null";
            logger.warn(msg);
            throw new ServiceException(msg);
        }
        subjectRepository.update(subject.getName(), subject.getDescription(), subject.getSupervisor(), subject.getId());
    }

    public void addTeacher(Subject subject, Teacher teacher) {
        logger.debug("Start service for adding teacher to the subject");
        if (subject == null || teacher == null) {
            String msg = "Subject and Teacher can't be null";
            logger.warn(msg);
            throw new ServiceException(msg);
        }
        subject.addTeacher(teacher);
    }

    public void deleteTeacher(Subject subject, Teacher teacher) {
        logger.debug("Start service for deleting teacher from the subject ");
        if (subject == null || teacher == null) {
            String msg = "Subject and Teacher can't be null";
            logger.warn(msg);
            throw new ServiceException(msg);
        }
        subject.deleteTeacher(teacher);
    }
}
