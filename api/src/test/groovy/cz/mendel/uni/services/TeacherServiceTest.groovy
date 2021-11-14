package cz.mendel.uni.services

import cz.mendel.uni.entities.Teacher
import cz.mendel.uni.repositories.TeacherRepository
import cz.mendel.uni.services.exceptions.ServiceException
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@EnableAutoConfiguration
@SpringBootTest
class TeacherServiceTest extends Specification{
    private TeacherService teacherService
    private TeacherRepository teacherRepository
    private Teacher teacher

    def setup(){
        teacherRepository = Mock()
        teacherService = new TeacherService(teacherRepository)
        teacher = new Teacher()
    }
//todo fix test
    def "TeacherRepository is used in findById(long) "(){
        given:
        teacherRepository.findById(0) >> teacher
        when:
        teacherService.findById(0)
        then:
        1 * teacherRepository.findById(0)
    }

    def "TeacherRepository is used in save(Classroom) "(){
        when:
        teacherService.save(teacher)
        then:
        1 * teacherRepository.save(teacher)
    }

    def "TeacherRepository is used in update(Classroom) "(){
        when:
        teacherService.update(teacher)
        then:
        1 * teacherRepository.update(teacher.getFirstname(), teacher.getLastname(), teacher.getGender(), teacher.getDateOfBirth(), teacher.getWorkingHours(),
                teacher.getVacation(), teacher.getId())
    }

    def "TeacherRepository is used in findAll() "(){
        when:
        teacherService.findAll()
        then:
        1 * teacherRepository.findAll()
    }

    def "TeacherRepository is used in deleteById(long) "(){
        when:
        teacherService.deleteById(1)
        then:
        1 * teacherRepository.deleteById(1)
    }

    def "Save null cause @ServiceException"(){
        when:
        teacherService.save(null)
        then:
        thrown(ServiceException)
    }

    def "Update null cause @ServiceException"(){
        when:
        teacherService.update(null)
        then:
        thrown(ServiceException)
    }
}
