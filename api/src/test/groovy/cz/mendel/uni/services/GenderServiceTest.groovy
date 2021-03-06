package cz.mendel.uni.services

import cz.mendel.uni.entities.Gender
import cz.mendel.uni.repositories.GenderRepository
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@EnableAutoConfiguration
@SpringBootTest
class GenderServiceTest extends Specification {
    private GenderService genderService
    private GenderRepository genderRepository
    private Gender gender

    def setup() {
        genderRepository = Mock()
        genderService = new GenderService(genderRepository)
        gender = new Gender()
    }

    def "GenderRepository is used in findById(long) "() {
        given:
        GenderRepository stubRepo = Stub(GenderRepository)
        GenderService service = new GenderService(stubRepo)
        stubRepo.findById(1) >> Optional.of(gender)
        when:
        Gender testGender = service.findById(1)
        then:
        testGender == gender
    }

    def "GenderRepository is used in save(Gender) "() {
        when:
        genderService.save(gender)
        then:
        1 * genderRepository.save(gender)
    }

    def "GenderRepository is used in update(Gender) "() {
        when:
        genderService.update(gender)
        then:
        1 * genderRepository.save(gender)
    }

    def "GenderRepository is used in findAll() "() {
        when:
        genderService.findAll()
        then:
        1 * genderRepository.findAll()
    }

    def "GenderRepository is used in deleteById(long) "() {
        when:
        genderService.deleteById(1)
        then:
        1 * genderRepository.deleteById(1)
    }
}
