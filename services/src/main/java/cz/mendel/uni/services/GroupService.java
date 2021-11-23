package cz.mendel.uni.services;

import cz.mendel.uni.entities.Group;
import cz.mendel.uni.entities.Student;
import cz.mendel.uni.repositories.GroupRepository;
import cz.mendel.uni.services.exceptions.ServiceException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@AllArgsConstructor
public class GroupService {
    private GroupRepository groupRepository;

    public Group findById(long id) {
        log.debug("Start service for getting group id {}", id);
        return groupRepository.findById(id).orElseThrow(() -> {
            String msg = String.format("Group with Id [%s] doesn't exist", id);
            log.warn(msg);
            throw new ServiceException(msg);
        });
    }

    public Group save(Group group) {
        log.debug("Start service for saving group");
        if (group == null) {
            String msg = "Group can't be null";
            log.warn(msg);
            throw new ServiceException(msg);
        }
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

    public void update(Group group) {
        log.debug("Start service for updating group");
        if (group == null) {
            String msg = "Group can't be null";
            log.warn(msg);
            throw new ServiceException(msg);
        }
        groupRepository.save(group);
    }

    public List<Student> findStudents(Group group) {
        log.debug("Start service for getting all students from the group");
        if (group == null) {
            String msg = "Group can't be null";
            log.warn(msg);
            throw new ServiceException(msg);
        }
        return group.getStudents();
    }

    public void addStudent(Student student, Group group) {
        log.debug("Start service for adding student to the group");
        if (group == null || student == null) {
            String msg = "Group and Student can't be null";
            log.warn(msg);
            throw new ServiceException(msg);
        }
        group.getStudents().add(student);
    }

    public void deleteStudent(Student student, Group group) {
        log.debug("Start service for deleting student from the group");
        if (group == null || student == null) {
            String msg = "Group and Student can't be null";
            log.warn(msg);
            throw new ServiceException(msg);
        }
        group.getStudents().remove(student);
    }
}
