package cz.mendel.uni.services

import cz.mendel.uni.entities.Teacher
import cz.mendel.uni.repositories.TeacherRepository
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@EnableAutoConfiguration
@SpringBootTest
class TeacherServiceTest extends Specification {
    private TeacherService teacherService
    private TeacherRepository teacherRepository
    private Teacher teacher

    def setup() {
        teacherRepository = Mock()
        teacherService = new TeacherService(teacherRepository)
        teacher = new Teacher()
    }

    def "TeacherRepository is used in findById(long) "() {
        given:
        TeacherRepository stubRepo = Stub(TeacherRepository)
        TeacherService service = new TeacherService(stubRepo)
        stubRepo.findById(1) >> Optional.of(teacher)
        when:
        Teacher testTeacher = service.findById(1)
        then:
        testTeacher == teacher
    }

    def "TeacherRepository is used in save(Classroom) "() {
        when:
        teacherService.save(teacher)
        then:
        1 * teacherRepository.save(teacher)
    }

    def "TeacherRepository is used in update(Classroom) "() {
        when:
        teacherService.update(teacher)
        then:
        1 * teacherRepository.save(teacher)
    }

    def "TeacherRepository is used in findAll() "() {
        when:
        teacherService.findAll()
        then:
        1 * teacherRepository.findAll()
    }

    def "TeacherRepository is used in deleteById(long) "() {
        when:
        teacherService.deleteById(1)
        then:
        1 * teacherRepository.deleteById(1)
    }
}
