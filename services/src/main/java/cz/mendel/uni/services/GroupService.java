package cz.mendel.uni.services;

import cz.mendel.uni.entities.Group;
import cz.mendel.uni.entities.Student;
import cz.mendel.uni.repositories.GroupRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@AllArgsConstructor
public class GroupService {
    private GroupRepository groupRepository;

    public Group findById(long id) {
        log.debug("Start service for getting group id {}", id);
        return groupRepository.findById(id).orElse(null);
    }

    public Group save(@NonNull Group group) {
        log.debug("Start service for saving group");
        return groupRepository.save(group);
    }

    public List<Group> findAll() {
        log.debug("Start service for getting all groups");
        return groupRepository.findAll();
    }

    public void deleteById(long id) {
        log.debug("Start service for deleting group id {}", id);
        groupRepository.deleteById(id);
    }

    public void update(@NonNull Group group) {
        log.debug("Start service for updating group");
        groupRepository.save(group);
    }

    public List<Student> findStudents(@NonNull Group group) {
        log.debug("Start service for getting all students from the group");
        return group.getStudents();
    }

    public void addStudent(@NonNull Student student, @NonNull Group group) {
        log.debug("Start service for adding student to the group");
        group.getStudents().add(student);
    }

    public void deleteStudent(@NonNull Student student, @NonNull Group group) {
        log.debug("Start service for deleting student from the group");
        group.getStudents().remove(student);
    }
}
