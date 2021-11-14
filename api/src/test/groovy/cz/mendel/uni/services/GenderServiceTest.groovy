package cz.mendel.uni.services

import cz.mendel.uni.entities.Gender
import cz.mendel.uni.repositories.GenderRepository
import cz.mendel.uni.services.exceptions.ServiceException
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@EnableAutoConfiguration
@SpringBootTest
class GenderServiceTest extends Specification{
    private GenderService genderService
    private GenderRepository genderRepository
    private Gender gender;

    def setup(){
        genderRepository = Mock()
        genderService = new GenderService(genderRepository)
        gender = new Gender()
    }
//todo fix test
    def "GenderRepository is used in findById(long) "(){
        given:
        genderRepository.findById(0) >> gender
        when:
        genderService.findById(0)
        then:
        1 * genderRepository.findById(0)
    }

    def "GenderRepository is used in save(Gender) "(){
        when:
        genderService.save(gender)
        then:
        1 * genderRepository.save(gender)
    }

    def "GenderRepository is used in update(Gender) "(){
        when:
        genderService.update(gender)
        then:
        1 * genderRepository.update(gender.getName(), 0)
    }

    def "GenderRepository is used in findAll() "(){
        when:
        genderService.findAll()
        then:
        1 * genderRepository.findAll()
    }

    def "GenderRepository is used in deleteById(long) "(){
        when:
        genderService.deleteById(1)
        then:
        1 * genderRepository.deleteById(1)
    }

    def "Save null cause @ServiceException"(){
        when:
        genderService.save(null)
        then:
        thrown(ServiceException)
    }

    def "Update null cause @ServiceException"(){
        when:
        genderService.update(null)
        then:
        thrown(ServiceException)
    }
}
