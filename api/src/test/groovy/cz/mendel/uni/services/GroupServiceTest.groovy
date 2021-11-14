package cz.mendel.uni.services

import cz.mendel.uni.entities.Group
import cz.mendel.uni.repositories.GroupRepository
import cz.mendel.uni.services.exceptions.ServiceException
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@EnableAutoConfiguration
@SpringBootTest
class GroupServiceTest extends Specification{
    private GroupService groupService
    private GroupRepository groupRepository
    private Group group;

    def setup(){
        groupRepository = Mock()
        groupService = new GroupService(groupRepository)
        group = new Group()
    }
//todo fix test
    def "GroupRepository is used in findById(long) "(){
        given:
        groupRepository.findById(0) >> group
        when:
        groupService.findById(0)
        then:
        1 * groupRepository.findById(0)
    }

    def "GroupRepository is used in save(Group) "(){
        when:
        groupService.save(group)
        then:
        1 * groupRepository.save(group)
    }

    def "GroupRepository is used in update(Group) "(){
        when:
        groupService.update(group)
        then:
        1 * groupRepository.update(group.getName(), 0)
    }

    def "GroupRepository is used in findAll() "(){
        when:
        groupService.findAll()
        then:
        1 * groupRepository.findAll()
    }

    def "GroupRepository is used in deleteById(long) "(){
        when:
        groupService.deleteById(1)
        then:
        1 * groupRepository.deleteById(1)
    }

    def "Save null cause @ServiceException"(){
        when:
        groupService.save(null)
        then:
        thrown(ServiceException)
    }

    def "Update null cause @ServiceException"(){
        when:
        groupService.update(null)
        then:
        thrown(ServiceException)
    }

    def "Find students with null Group cause @ServiceException"(){
        when:
        groupService.findStudents(null)
        then:
        thrown(ServiceException)
    }

    def "Add student with null Group and Student cause @ServiceException"(){
        when:
        groupService.addStudent(null, null)
        then:
        thrown(ServiceException)
    }

    def "Delete student with null Group and Student cause @ServiceException"(){
        when:
        groupService.deleteStudent(null, null)
        then:
        thrown(ServiceException)
    }
}
