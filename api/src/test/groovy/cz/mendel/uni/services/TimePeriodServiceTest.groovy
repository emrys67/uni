package cz.mendel.uni.services

import cz.mendel.uni.entities.TimePeriod
import cz.mendel.uni.repositories.TimePeriodRepository
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@EnableAutoConfiguration
@SpringBootTest
class TimePeriodServiceTest extends Specification {
    private TimePeriodService timePeriodService
    private TimePeriodRepository timePeriodRepository
    private TimePeriod timePeriod

    def setup() {
        timePeriodRepository = Mock()
        timePeriodService = new TimePeriodService(timePeriodRepository)
        timePeriod = new TimePeriod()
    }

    def "TimePeriodRepository is used in findById(long) "() {
        given:
        TimePeriodRepository stubRepo = Stub(TimePeriodRepository)
        TimePeriodService service = new TimePeriodService(stubRepo)
        stubRepo.findById(1) >> Optional.of(timePeriod)
        when:
        TimePeriod testTimePeriod = service.findById(1)
        then:
        testTimePeriod == timePeriod
    }

    def "TimePeriodRepository is used in save(TimePeriod) "() {
        when:
        timePeriodService.save(timePeriod)
        then:
        1 * timePeriodRepository.save(timePeriod)
    }

    def "TimePeriodRepository is used in update(TimePeriod) "() {
        when:
        timePeriodService.update(timePeriod)
        then:
        1 * timePeriodRepository.save(timePeriod)
    }

    def "TimePeriodRepository is used in findAll() "() {
        when:
        timePeriodService.findAll()
        then:
        1 * timePeriodRepository.findAll()
    }

    def "TimePeriodRepository is used in deleteById(long) "() {
        when:
        timePeriodService.deleteById(1)
        then:
        1 * timePeriodRepository.deleteById(1)
    }
}
