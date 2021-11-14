package cz.mendel.uni.services

import cz.mendel.uni.entities.Classroom
import cz.mendel.uni.repositories.ClassroomRepository
import cz.mendel.uni.services.exceptions.ServiceException
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@EnableAutoConfiguration
@SpringBootTest
class ClassroomServiceTest extends Specification{
    private ClassroomService classroomService
    private ClassroomRepository classroomRepository
    private Classroom classroom;

    def setup(){
        classroomRepository = Mock()
        classroomService = new ClassroomService(classroomRepository)
        classroom = new Classroom()
    }
//todo fix test
    def "ClassroomRepository is used in findById(long) "(){
        given:
        classroomRepository.findById(0) >> classroom
        when:
        classroomService.findById(0)
        then:
        1 * classroomRepository.findById(0)
    }

    def "ClassroomRepository is used in save(Classroom) "(){
        when:
        classroomService.save(classroom)
        then:
        1 * classroomRepository.save(classroom)
    }

    def "ClassroomRepository is used in update(Classroom) "(){
        when:
        classroomService.update(classroom)
        then:
        1 * classroomRepository.update(0, 0)
    }

    def "ClassroomRepository is used in findAll() "(){
        when:
        classroomService.findAll()
        then:
        1 * classroomRepository.findAll()
    }

    def "ClassroomRepository is used in deleteById(long) "(){
        when:
        classroomService.deleteById(1)
        then:
        1 * classroomRepository.deleteById(1)
    }

    def "Save null cause @ServiceException"(){
        when:
        classroomService.save(null)
        then:
        thrown(ServiceException)
    }

    def "Update null cause @ServiceException"(){
        when:
        classroomService.update(null)
        then:
        thrown(ServiceException)
    }

}