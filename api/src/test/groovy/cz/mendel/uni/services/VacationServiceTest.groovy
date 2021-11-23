package cz.mendel.uni.services

import cz.mendel.uni.entities.Vacation
import cz.mendel.uni.repositories.VacationRepository
import cz.mendel.uni.services.exceptions.ServiceException
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@EnableAutoConfiguration
@SpringBootTest
class VacationServiceTest extends Specification{
    private VacationService vacationService
    private VacationRepository vacationRepository
    private Vacation vacation;

    def setup(){
        vacationRepository = Mock()
        vacationService = new VacationService(vacationRepository)
        vacation = new Vacation()
    }

    def "VacationRepository is used in findById(long) "(){
        given:
        VacationRepository stubRepo = Stub(VacationRepository)
        VacationService service = new VacationService(stubRepo)
        stubRepo.findById(1) >> Optional.of(vacation)
        when:
        Vacation testVacation = service.findById(1)
        then:
        testVacation == vacation
    }

    def "VacationRepository is used in save(Vacation) "(){
        when:
        vacationService.save(vacation)
        then:
        1 * vacationRepository.save(vacation)
    }

    def "VacationRepository is used in update(Vacation) "(){
        when:
        vacationService.update(vacation)
        then:
        1 * vacationRepository.update(vacation.getDescription(), vacation.getTimePeriod(), vacation.getId())
    }

    def "VacationRepository is used in findAll() "(){
        when:
        vacationService.findAll()
        then:
        1 * vacationRepository.findAll()
    }

    def "VacationRepository is used in deleteById(long) "(){
        when:
        vacationService.deleteById(1)
        then:
        1 * vacationRepository.deleteById(1)
    }

    def "Save null cause @ServiceException"(){
        when:
        vacationService.save(null)
        then:
        thrown(ServiceException)
    }

    def "Update null cause @ServiceException"(){
        when:
        vacationService.update(null)
        then:
        thrown(ServiceException)
    }
}
