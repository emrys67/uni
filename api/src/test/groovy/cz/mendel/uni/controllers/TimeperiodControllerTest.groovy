package cz.mendel.uni.controllers

import cz.mendel.uni.controllers.exceptions.ExceptionHandlerController
import cz.mendel.uni.entities.TimePeriod
import cz.mendel.uni.services.TimePeriodService
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
class TimeperiodControllerTest extends Specification {
    @Autowired
    private MockMvc mockMvc
    private TimePeriod timeperiod = new TimePeriod()
    private TimePeriodService timePeriodService
    private TimeperiodController timeperiodController

    def setup() {
        timePeriodService = Mock()
        timeperiodController = new TimeperiodController(timePeriodService)
        mockMvc = MockMvcBuilders.standaloneSetup(timeperiodController).setControllerAdvice(new ExceptionHandlerController()).build()
    }

    def "Status is OK and model has attribute TimePeriod and view returned for /timeperiod/{id}"() {
        given:
        mockMvc = MockMvcBuilders.standaloneSetup(timeperiodController).setControllerAdvice(new ExceptionHandlerController()).build()
        timePeriodService.findById(1) >> timeperiod
        expect: "status is ok"
        mockMvc.perform(MockMvcRequestBuilders.get("/timeperiod/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("timeperiod", timeperiod))
                .andExpect(MockMvcResultMatchers.view().name("timeperiods/timeperiod-info"))
    }

    def "TimePeriodService is used in /timeperiod{id}"() {
        when:
        mockMvc.perform(MockMvcRequestBuilders.get("/timeperiod/1"))
        then:
        1 * timePeriodService.findById(1)
    }

    def "Status is OK and model has attribute TimePeriod and view returned for /timeperiod/add/new"() {
        given:
        mockMvc = MockMvcBuilders.standaloneSetup(timeperiodController).setControllerAdvice(new ExceptionHandlerController()).build()
        expect: "status is ok"
        mockMvc.perform(MockMvcRequestBuilders.get("/timeperiod/add/new"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("timeperiod", timeperiod))
                .andExpect(MockMvcResultMatchers.view().name("timeperiods/add-timeperiod"))
    }

    def "TimePeriodService is used in /timeperiod/add"() {
        when:
        mockMvc.perform(MockMvcRequestBuilders.post("/timeperiod/add"))
        then:
        1 * timePeriodService.save(*_)
    }

    def "Status is OK and model has attribute TimePeriod and view returned for /timeperiod/edit/{id}"() {
        given:
        mockMvc = MockMvcBuilders.standaloneSetup(timeperiodController).setControllerAdvice(new ExceptionHandlerController()).build()
        timePeriodService.findById(1) >> timeperiod
        expect: "status is ok"
        mockMvc.perform(MockMvcRequestBuilders.get("/timeperiod/edit/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("timeperiod", timeperiod))
                .andExpect(MockMvcResultMatchers.view().name("timeperiods/edit-timeperiod"))
    }

    def "TimePeriodService is used in /timeperiod/edit/{id}"() {
        when:
        mockMvc.perform(MockMvcRequestBuilders.get("/timeperiod/edit/1"))
        then:
        1 * timePeriodService.findById(1)
    }

    def "TimePeriodService is used in /timeperiod/edit"() {
        when:
        mockMvc.perform(MockMvcRequestBuilders.post("/timeperiod/edit"))
        then:
        1 * timePeriodService.update(*_)
    }

    def "TimePeriodService is used in /timeperiod/delete/{id}"() {
        when:
        mockMvc.perform(MockMvcRequestBuilders.get("/timeperiod/delete/1"))
        then:
        1 * timePeriodService.deleteById(*_)
    }
}
