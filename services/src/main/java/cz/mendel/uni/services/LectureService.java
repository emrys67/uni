package cz.mendel.uni.services;

import cz.mendel.uni.entities.Group;
import cz.mendel.uni.entities.Lecture;
import cz.mendel.uni.repositories.LectureRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@AllArgsConstructor
public class LectureService {
    private LectureRepository lectureRepository;

    public Lecture findById(long id) {
        log.debug("Start service for getting lecture id {}", id);
        return lectureRepository.findById(id).orElse(null);
    }

    public Lecture save(@NonNull Lecture lecture) {
        log.debug("Start service for saving lecture");
        return lectureRepository.save(lecture);
    }

    public List<Lecture> findAll() {
        log.debug("Start service for getting all lectures");
        return lectureRepository.findAll();
    }

    public void deleteById(long id) {
        log.debug("Start service for deleting lecture id {}", id);
        lectureRepository.deleteById(id);
    }

    public void update(@NonNull Lecture lecture) {
        log.debug("Start service for updating lecture");
        lectureRepository.save(lecture);
    }

    public void addGroup(@NonNull Lecture lecture, Group group) {
        log.debug("Start service for adding group to the lecture");
        lecture.getGroups().add(group);
    }

    public void deleteGroup(@NonNull Lecture lecture, Group group) {
        log.debug("Start service for deleting group from the lecture");
        lecture.getGroups().remove(group);
    }
}
