package cz.mendel.uni.services;

import cz.mendel.uni.entities.Subject;
import cz.mendel.uni.entities.Teacher;
import cz.mendel.uni.repositories.SubjectRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@AllArgsConstructor
public class SubjectService {
    private SubjectRepository subjectRepository;

    public Subject findById(long id) {
        log.debug("Start service for getting subject id {}", id);
        return subjectRepository.findById(id).orElse(null);
    }

    @NonNull
    public Subject save(Subject subject) {
        log.debug("Start service for saving subject");
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

    @NonNull
    public void update(Subject subject) {
        log.debug("Start service for updating subject");
        subjectRepository.save(subject);
    }

    @NonNull
    public void addTeacher(Subject subject, Teacher teacher) {
        log.debug("Start service for adding teacher to the subject");
        subject.addTeacher(teacher);
    }

    @NonNull
    public void deleteTeacher(Subject subject, Teacher teacher) {
        log.debug("Start service for deleting teacher from the subject ");
        subject.deleteTeacher(teacher);
    }
}
