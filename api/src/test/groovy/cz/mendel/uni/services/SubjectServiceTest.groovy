package cz.mendel.uni.services

import cz.mendel.uni.entities.Subject
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
}
