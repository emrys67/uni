package cz.mendel.uni.services

import cz.mendel.uni.entities.TimePeriod
import cz.mendel.uni.repositories.TimePeriodRepository
import cz.mendel.uni.services.exceptions.ServiceException
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@EnableAutoConfiguration
@SpringBootTest
class TimePeriodServiceTest extends Specification{
    private TimePeriodService timePeriodService
    private TimePeriodRepository timePeriodRepository
    private TimePeriod timePeriod

    def setup(){
        timePeriodRepository = Mock()
        timePeriodService = new TimePeriodService(timePeriodRepository)
        timePeriod = new TimePeriod()
    }
//todo fix test
    def "TimePeriodRepository is used in findById(long) "(){
        given:
        timePeriodRepository.findById(0) >> timePeriod
        when:
        timePeriodService.findById(0)
        then:
        1 * timePeriodRepository.findById(0)
    }

    def "TimePeriodRepository is used in save(TimePeriod) "(){
        when:
        timePeriodService.save(timePeriod)
        then:
        1 * timePeriodRepository.save(timePeriod)
    }

    def "TimePeriodRepository is used in update(TimePeriod) "(){
        when:
        timePeriodService.update(timePeriod)
        then:
        1 * timePeriodRepository.update(timePeriod.getStartDate(), timePeriod.getStartTime(), timePeriod.getEndTime(),
                timePeriod.getEndDate(), timePeriod.getId())
    }

    def "TimePeriodRepository is used in findAll() "(){
        when:
        timePeriodService.findAll()
        then:
        1 * timePeriodRepository.findAll()
    }

    def "TimePeriodRepository is used in deleteById(long) "(){
        when:
        timePeriodService.deleteById(1)
        then:
        1 * timePeriodRepository.deleteById(1)
    }

    def "Save null cause @ServiceException"(){
        when:
        timePeriodService.save(null)
        then:
        thrown(ServiceException)
    }

    def "Update null cause @ServiceException"(){
        when:
        timePeriodService.update(null)
        then:
        thrown(ServiceException)
    }
}
