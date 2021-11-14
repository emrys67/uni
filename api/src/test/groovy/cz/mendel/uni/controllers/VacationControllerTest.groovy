package cz.mendel.uni.controllers

import cz.mendel.uni.controllers.exceptions.ExceptionHandlerController
import cz.mendel.uni.entities.TimePeriod
import cz.mendel.uni.entities.Vacation
import cz.mendel.uni.services.TimePeriodService
import cz.mendel.uni.services.VacationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

@SpringBootTest
@AutoConfigureMockMvc
@EnableAutoConfiguration
class VacationControllerTest extends Specification {
    @Autowired
    private MockMvc mockMvc
    private Vacation vacation = new Vacation()
    private VacationService vacationService
    private TimePeriodService timePeriodService;
    private VacationController vacationController

    def setup() {
        vacationService = Mock()
        timePeriodService = Mock()
        vacationController = new VacationController(vacationService, timePeriodService)
        mockMvc = MockMvcBuilders.standaloneSetup(vacationController).setControllerAdvice(new ExceptionHandlerController()).build()
    }

    def "Status is OK and model has attribute Vacation and view returned for /vacation/{id}"() {
        given:
        mockMvc = MockMvcBuilders.standaloneSetup(vacationController).setControllerAdvice(new ExceptionHandlerController()).build();
        vacationService.findById(1) >> vacation
        expect: "status is ok"
        mockMvc.perform(MockMvcRequestBuilders.get("/vacation/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("vacation", vacation))
                .andExpect(MockMvcResultMatchers.view().name("vacations/vacation-info"))
    }

    def "VacationService is used in /vacation{id}"() {
        when:
        mockMvc.perform(MockMvcRequestBuilders.get("/vacation/1"))
        then:
        1 * vacationService.findById(1);
    }

    def "Status is OK and model has attribute Vacation and view returned for /vacation/add/new"() {
        given:
        mockMvc = MockMvcBuilders.standaloneSetup(vacationController).setControllerAdvice(new ExceptionHandlerController()).build();
        expect: "status is ok"
        mockMvc.perform(MockMvcRequestBuilders.get("/vacation/add/new"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("vacation", Vacation.builder().timePeriod(new TimePeriod()).build()))
                .andExpect(MockMvcResultMatchers.view().name("vacations/add-vacation"))
    }

    def "VacationService and TimePeriodService are used in /vacation/add"() {
        when:
        mockMvc.perform(MockMvcRequestBuilders.post("/vacation/add"))
        then:
        1 * vacationService.save(*_);
        1 * timePeriodService.save(*_)
    }

    def "Status is OK and model has attribute Vacation and view returned for /vacation/edit/{id}"() {
        given:
        mockMvc = MockMvcBuilders.standaloneSetup(vacationController).setControllerAdvice(new ExceptionHandlerController()).build();
        vacationService.findById(1) >> vacation
        expect: "status is ok"
        mockMvc.perform(MockMvcRequestBuilders.get("/vacation/edit/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("vacation", vacation))
                .andExpect(MockMvcResultMatchers.view().name("vacations/edit-vacation"))
    }

    def "VacationService is used in /vacation/edit/{id}"() {
        when:
        mockMvc.perform(MockMvcRequestBuilders.get("/vacation/edit/1"))
        then:
        1 * vacationService.findById(1);
    }

    def "VacationService is used in /vacation/edit"() {
        when:
        mockMvc.perform(MockMvcRequestBuilders.post("/vacation/edit"))
        then:
        1 * vacationService.update(*_);
    }

    def "VacationService is used in /vacation/delete/{id}"() {
        when:
        mockMvc.perform(MockMvcRequestBuilders.get("/vacation/delete/1"))
        then:
        1 * vacationService.deleteById(*_);
    }
}
