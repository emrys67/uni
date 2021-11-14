package cz.mendel.uni.services

import cz.mendel.uni.entities.Lecture
import cz.mendel.uni.repositories.LectureRepository
import cz.mendel.uni.services.exceptions.ServiceException
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@EnableAutoConfiguration
@SpringBootTest
class LectureServiceTest extends Specification{
    private LectureService lectureService
    private LectureRepository lectureRepository
    private Lecture lecture;

    def setup(){
        lectureRepository = Mock()
        lectureService = new LectureService(lectureRepository)
        lecture = new Lecture()
    }
//todo fix test
    def "LectureRepository is used in findById(long) "(){
        given:
        lectureRepository.findById(0) >> lecture
        when:
        lectureService.findById(0)
        then:
        1 * lectureRepository.findById(0)
    }

    def "LectureRepository is used in save(Lecture) "(){
        when:
        lectureService.save(lecture)
        then:
        1 * lectureRepository.save(lecture)
    }

    def "LectureRepository is used in update(Lecture) "(){
        when:
        lectureService.update(lecture)
        then:
        1 * lectureRepository.update(lecture.getSubject(), lecture.getClassroom(), lecture.getTimePeriod(),
                lecture.getTeacher(), lecture.getId())
    }

    def "LectureRepository is used in findAll() "(){
        when:
        lectureService.findAll()
        then:
        1 * lectureRepository.findAll()
    }

    def "LectureRepository is used in deleteById(long) "(){
        when:
        lectureService.deleteById(1)
        then:
        1 * lectureRepository.deleteById(1)
    }

    def "Save null cause @ServiceException"(){
        when:
        lectureService.save(null)
        then:
        thrown(ServiceException)
    }

    def "Update null cause @ServiceException"(){
        when:
        lectureService.update(null)
        then:
        thrown(ServiceException)
    }

    def "Add group with null Lecture and Group case @ServiceException"(){
        when:
        lectureService.addGroup(null, null)
        then:
        thrown(ServiceException)
    }

    def "Delete group with null Lecture and Group case @ServiceException"(){
        when:
        lectureService.deleteGroup(null, null)
        then:
        thrown(ServiceException)
    }
}
