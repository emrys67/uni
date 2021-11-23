package cz.mendel.uni.services

import cz.mendel.uni.entities.Subject
import cz.mendel.uni.repositories.SubjectRepository
import cz.mendel.uni.services.exceptions.ServiceException
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@EnableAutoConfiguration
@SpringBootTest
class SubjectServiceTest extends Specification {
    private SubjectService subjectService
    private SubjectRepository subjectRepository
    private Subject subject;

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
        1 * subjectRepository.update(subject.getName(), subject.getDescription(), subject.getSupervisor(), subject.getId())
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

    def "Save null cause @ServiceException"(){
        when:
        subjectService.save(null)
        then:
        thrown(ServiceException)
    }

    def "Update null cause @ServiceException"(){
        when:
        subjectService.update(null)
        then:
        thrown(ServiceException)
    }

    def "Add teacher with null Subject and Teacher cause @ServiceException"(){
        when:
        subjectService.addTeacher(null, null)
        then:
        thrown(ServiceException)
    }

    def "Delete teacher with null Subject and Teacher cause @ServiceException"(){
        when:
        subjectService.deleteTeacher(null, null)
        then:
        thrown(ServiceException)
    }
}
