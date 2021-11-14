package cz.mendel.uni.services

import cz.mendel.uni.entities.Student
import cz.mendel.uni.repositories.StudentRepository
import cz.mendel.uni.services.exceptions.ServiceException
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@EnableAutoConfiguration
@SpringBootTest
class StudentServiceTest extends Specification{
    private StudentService studentService
    private StudentRepository studentRepository
    private Student student;

    def setup(){
        studentRepository = Mock()
        studentService = new StudentService(studentRepository)
        student = new Student()
    }
//todo fix test
    def "StudentRepository is used in findById(long) "(){
        given:
        studentRepository.findById(0) >> student
        when:
        studentService.findById(0)
        then:
        1 * studentRepository.findById(0)
    }

    def "StudentRepository is used in save(Student) "(){
        when:
        studentService.save(student)
        then:
        1 * studentRepository.save(student)
    }

    def "StudentRepository is used in update(Student) "(){
        when:
        studentService.update(student)
        then:
        1 * studentRepository.update(student.getFirstname(), student.getLastname(), student.getGender(),
                student.getDateOfBirth(), student.getStudyYear(), student.getGroup(), student.getId())
    }

    def "StudentRepository is used in findAll() "(){
        when:
        studentService.findAll()
        then:
        1 * studentRepository.findAll()
    }

    def "StudentRepository is used in deleteById(long) "(){
        when:
        studentService.deleteById(1)
        then:
        1 * studentRepository.deleteById(1)
    }

    def "Save null cause @ServiceException"(){
        when:
        studentService.save(null)
        then:
        thrown(ServiceException)
    }

    def "Update null cause @ServiceException"(){
        when:
        studentService.update(null)
        then:
        thrown(ServiceException)
    }
}
