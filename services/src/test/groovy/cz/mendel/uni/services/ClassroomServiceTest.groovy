package cz.mendel.uni.services

import cz.mendel.uni.repositories.ClassroomRepository
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@EnableAutoConfiguration
@SpringBootTest
class ClassroomServiceTest extends Specification{
    private ClassroomService classroomService;
    private ClassroomRepository classroomRepository;

    def setup(){
        classroomRepository = Mock();
        classroomService = new ClassroomService(classroomRepository);
    }

    def "find by id is used"(){
        when:
        classroomService.findById(*_)
        then:
        1 * classroomRepository.findById(*_)
    }
}
