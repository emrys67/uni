package cz.mendel.uni.services;

import cz.mendel.uni.entities.Subject;
import cz.mendel.uni.entities.Teacher;
import cz.mendel.uni.repositories.SubjectRepository;
import cz.mendel.uni.services.exceptions.ServiceException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@AllArgsConstructor
public class SubjectService {
    private SubjectRepository subjectRepository;

    public Subject findById(long id) {
        log.debug("Start service for getting subject id {}", id);
        return subjectRepository.findById(id).orElseThrow(() -> {
            String msg = String.format("Subject with Id [%s] doesn't exist", id);
            log.warn(msg);
            throw new ServiceException(msg);
        });
    }

    public Subject save(Subject subject) {
        log.debug("Start service for saving subject");
        if (subject == null) {
            String msg = "Subject can't be null";
            log.warn(msg);
            throw new ServiceException(msg);
        }
        return subjectRepository.save(subject);
    }

    public List<Subject> findAll() {
        log.debug("Start service for getting all subjects");
        return subjectRepository.findAll();
    }

    public void deleteById(long id) {
        log.debug("Start service for deleting subject id {}", id);
        subjectRepository.deleteById(id);
    }

    public void update(Subject subject) {
        log.debug("Start service for updating subject");
        if (subject == null) {
            String msg = "Subject can't be null";
            log.warn(msg);
            throw new ServiceException(msg);
        }
        subjectRepository.save(subject);
    }

    public void addTeacher(Subject subject, Teacher teacher) {
        log.debug("Start service for adding teacher to the subject");
        if (subject == null || teacher == null) {
            String msg = "Subject and Teacher can't be null";
            log.warn(msg);
            throw new ServiceException(msg);
        }
        subject.addTeacher(teacher);
    }

    public void deleteTeacher(Subject subject, Teacher teacher) {
        log.debug("Start service for deleting teacher from the subject ");
        if (subject == null || teacher == null) {
            String msg = "Subject and Teacher can't be null";
            log.warn(msg);
            throw new ServiceException(msg);
        }
        subject.deleteTeacher(teacher);
    }
}
