package cz.mendel.uni.services;


import cz.mendel.uni.entities.Group;
import cz.mendel.uni.entities.Lecture;
import cz.mendel.uni.repositories.LectureRepository;
import cz.mendel.uni.services.exceptions.ServiceException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
public class LectureService {
    private LectureRepository lectureRepository;

    public Lecture findById(long id) {
        log.debug("Start service for getting lecture id {}", id);
        return lectureRepository.findById(id).orElseThrow(() -> {
            String msg = String.format("Lecture with Id [%s] doesn't exist", id);
            log.warn(msg);
            throw new ServiceException(msg);
        });
    }

    public Lecture save(Lecture lecture) {
        log.debug("Start service for saving lecture");
        if (lecture == null) {
            String msg = "Lecture can't be null";
            log.warn(msg);
            throw new ServiceException(msg);
        }
        return lectureRepository.save(lecture);
    }

    public List<Lecture> findAll() {
        log.debug("Start service for getting all lectures");
        return lectureRepository.findAll();
    }

//    public List<Lecture> sortLectures(List<Lecture> list){
//        Comparator<Lecture> comparator = Comparator.comparing(lecture -> lecture.getTimePeriod().getStartDate());
//        comparator = comparator.thenComparing(Comparator.comparing(lecture -> lecture.getTimePeriod().getStartTime()));
//        return list.stream().sorted(comparator).collect(Collectors.toList());
//    }

    public void deleteById(long id) {
        log.debug("Start service for deleting lecture id {}", id);
        lectureRepository.deleteById(id);
    }

    public void update(Lecture lecture) {
        log.debug("Start service for updating lecture");
        if (lecture == null) {
            String msg = "Lecture can't be null";
            log.warn(msg);
            throw new ServiceException(msg);
        }
        lectureRepository.save(lecture);
    }

    public void addGroup(Lecture lecture, Group group) {
        log.debug("Start service for adding group to the lecture");
        if (lecture == null || group == null) {
            String msg = "Lecture and Group can't be null";
            log.warn(msg);
            throw new ServiceException(msg);
        }
        lecture.addGroup(group);
    }

    public void deleteGroup(Lecture lecture, Group group) {
        log.debug("Start service for deleting group from the lecture");
        if (lecture == null || group == null) {
            String msg = "Lecture and Group can't be null";
            log.warn(msg);
            throw new ServiceException(msg);
        }
        lecture.deleteGroup(group);
    }
}
