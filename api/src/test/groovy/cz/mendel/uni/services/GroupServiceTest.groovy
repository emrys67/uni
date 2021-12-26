package cz.mendel.uni.services

import cz.mendel.uni.entities.Group
import cz.mendel.uni.entities.Student
import cz.mendel.uni.repositories.GroupRepository
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@EnableAutoConfiguration
@SpringBootTest
class GroupServiceTest extends Specification {
    private GroupService groupService
    private GroupRepository groupRepository
    private Group group

    def setup() {
        groupRepository = Mock()
        groupService = new GroupService(groupRepository)
        group = new Group()
    }

    def "GroupRepository is used in findById(long) "() {
        given:
        GroupRepository stubRepo = Stub(GroupRepository)
        GroupService service = new GroupService(stubRepo)
        stubRepo.findById(1) >> Optional.of(group)
        when:
        Group testGroup = service.findById(1)
        then:
        testGroup == group
    }

    def "GroupRepository is used in save(Group) "() {
        when:
        groupService.save(group)
        then:
        1 * groupRepository.save(group)
    }

    def "GroupRepository is used in update(Group) "() {
        when:
        groupService.update(group)
        then:
        1 * groupRepository.save(group)
    }

    def "GroupRepository is used in findAll() "() {
        when:
        groupService.findAll()
        then:
        1 * groupRepository.findAll()
    }

    def "GroupRepository is used in deleteById(long) "() {
        when:
        groupService.deleteById(1)
        then:
        1 * groupRepository.deleteById(1)
    }

    def "Group.getStudents() is used in findStudents(Group) "() {
        given:
        Group groupMock = Mock()
        when:
        groupService.findStudents(groupMock)
        then:
        1 * groupMock.getStudents()
    }

    def "Student is added to Group.students when addStudent(Student, Group) is used"() {
        given:
        ArrayList<Student> arrayList = new ArrayList<Student>()
        Group groupMock = Stub()
        groupMock.getStudents() >> arrayList
        when:
        groupService.addStudent(new Student(), groupMock)
        then:
        arrayList.get(0) == new Student()
    }

    def "Student is removed from Group.students when deleteStudent(Student, Group) is used"() {
        given:
        ArrayList<Student> arrayList = new ArrayList<Student>()
        arrayList.add(new Student())
        Group groupMock = Stub()
        groupMock.getStudents() >> arrayList
        when:
        groupService.deleteStudent(new Student(), groupMock)
        then:
        arrayList.isEmpty()
    }
}
