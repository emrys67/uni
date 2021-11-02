package cz.mendel.uni.services;


import cz.mendel.uni.entities.Group;
import cz.mendel.uni.entities.Lecture;
import cz.mendel.uni.repositories.LectureRepository;
import cz.mendel.uni.services.exceptions.ServiceException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static java.lang.String.format;

@AllArgsConstructor
public class LectureService {
    private static final Logger logger = LoggerFactory.getLogger(LectureService.class.getName());
    private LectureRepository lectureRepository;

    public Lecture findById(long id) {
        logger.debug("Start service for getting lecture id {}", id);
        return lectureRepository.findById(id).orElseThrow(() -> {
            String msg = format("Lecture with Id [%s] doesn't exist", id);
            logger.warn(msg);
            throw new ServiceException(msg);
        });
    }

    public Lecture save(Lecture lecture) {
        logger.debug("Start service for saving lecture");
        if (lecture == null) {
            String msg = "Lecture can't be null";
            logger.warn(msg);
            throw new ServiceException(msg);
        }
        return lectureRepository.save(lecture);
    }

    public List<Lecture> findAll() {
        logger.debug("Start service for getting all lectures");
        return lectureRepository.findAll();
    }

    public void deleteById(long id) {
        logger.debug("Start service for deleting lecture id {}", id);
        lectureRepository.deleteById(id);
    }

    public void update(Lecture lecture) {
        logger.debug("Start service for updating lecture");
        if (lecture == null) {
            String msg = "Lecture can't be null";
            logger.warn(msg);
            throw new ServiceException(msg);
        }
        lectureRepository.update(lecture.getSubject(), lecture.getClassroom(), lecture.getTimePeriod(),
                lecture.getTeacher(), lecture.getId());
    }

    public void addGroup(Lecture lecture, Group group) {
        logger.debug("Start service for adding group to the lecture");
        if (lecture == null || group == null) {
            String msg = "Lecture and Group can't be null";
            logger.warn(msg);
            throw new ServiceException(msg);
        }
        lecture.addGroup(group);
    }

    public void deleteGroup(Lecture lecture, Group group) {
        logger.debug("Start service for deleting group from the lecture");
        if (lecture == null || group == null) {
            String msg = "Lecture and Group can't be null";
            logger.warn(msg);
            throw new ServiceException(msg);
        }
        lecture.deleteGroup(group);
    }
}
