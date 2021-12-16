package cz.mendel.uni.services

import cz.mendel.uni.entities.Subject
import cz.mendel.uni.entities.Teacher
import cz.mendel.uni.repositories.SubjectRepository
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@EnableAutoConfiguration
@SpringBootTest
class SubjectServiceTest extends Specification {
    private SubjectService subjectService
    private SubjectRepository subjectRepository
    private Subject subject

    def setup() {
        subjectRepository = Mock()
        subjectService = new SubjectService(subjectRepository)
        subject = new Subject()
    }

    def "SubjectRepository is used in findById(long) "() {
        given:
        SubjectRepository stubRepo = Stub(SubjectRepository)
        SubjectService service = new SubjectService(stubRepo)
        stubRepo.findById(1) >> Optional.of(subject)
        when:
        Subject testSubject = service.findById(1)
        then:
        testSubject == subject
    }

    def "SubjectRepository is used in save(Subject) "() {
        when:
        subjectService.save(subject)
        then:
        1 * subjectRepository.save(subject)
    }

    def "SubjectRepository is used in update(Subject) "() {
        when:
        subjectService.update(subject)
        then:
        1 * subjectRepository.save(subject)
    }

    def "SubjectRepository is used in findAll() "() {
        when:
        subjectService.findAll()
        then:
        1 * subjectRepository.findAll()
    }

    def "SubjectRepository is used in deleteById(long) "() {
        when:
        subjectService.deleteById(1)
        then:
        1 * subjectRepository.deleteById(1)
    }

    def "Teacher is added to Subject.teachers when addTeacher(Subject, Teacher) is used"() {
        given:
        ArrayList<Teacher> arrayList = new ArrayList<Teacher>()
        Subject subjectStub = Stub()
        subjectStub.getTeachers() >> arrayList
        when:
        subjectService.addTeacher(subjectStub, new Teacher())
        then:
        arrayList.get(0) == new Teacher()
    }

    def "Teacher is removed from Subject.teachers when deleteTeacher(Subject, Teacher) is used"() {
        given:
        ArrayList<Teacher> arrayList = new ArrayList<Teacher>()
        arrayList.add(new Teacher())
        Subject subjectStub = Stub()
        subjectStub.getTeachers() >> arrayList
        when:
        subjectService.deleteTeacher(subjectStub, new Teacher())
        then:
        arrayList.isEmpty()
    }
}
