package cz.mendel.uni.services

import cz.mendel.uni.entities.Group
import cz.mendel.uni.entities.Lecture
import cz.mendel.uni.repositories.LectureRepository
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@EnableAutoConfiguration
@SpringBootTest
class LectureServiceTest extends Specification {
    private LectureService lectureService
    private LectureRepository lectureRepository
    private Lecture lecture

    def setup() {
        lectureRepository = Mock()
        lectureService = new LectureService(lectureRepository)
        lecture = new Lecture()
    }

    def "LectureRepository is used in findById(long) "() {
        given:
        LectureRepository stubRepo = Stub(LectureRepository)
        LectureService service = new LectureService(stubRepo)
        stubRepo.findById(1) >> Optional.of(lecture)
        when:
        Lecture testLecture = service.findById(1)
        then:
        testLecture == lecture
    }

    def "LectureRepository is used in save(Lecture) "() {
        when:
        lectureService.save(lecture)
        then:
        1 * lectureRepository.save(lecture)
    }

    def "LectureRepository is used in update(Lecture) "() {
        when:
        lectureService.update(lecture)
        then:
        1 * lectureRepository.save(lecture)
    }

    def "LectureRepository is used in findAll() "() {
        when:
        lectureService.findAll()
        then:
        1 * lectureRepository.findAll()
    }

    def "LectureRepository is used in deleteById(long) "() {
        when:
        lectureService.deleteById(1)
        then:
        1 * lectureRepository.deleteById(1)
    }

    def "Group is added to Lecture.groups when addGroup(Lecture, Group) is used"() {
        given:
        ArrayList<Group> arrayList = new ArrayList<Group>()
        Lecture lectureStub = Stub()
        lectureStub.getGroups() >> arrayList
        when:
        lectureService.addGroup(lectureStub, new Group())
        then:
        arrayList.get(0) == new Group()
    }

    def "Group is removed from Lecture.groups when deleteGroup(Lecture, Group) is used"() {
        given:
        ArrayList<Group> arrayList = new ArrayList<Group>()
        arrayList.add(new Group())
        Lecture lectureStub = Stub()
        lectureStub.getGroups() >> arrayList
        when:
        lectureService.deleteGroup(lectureStub, new Group())
        then:
        arrayList.isEmpty()
    }
}
