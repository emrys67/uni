package cz.mendel.uni.controllers

import cz.mendel.uni.controllers.GenderController
import cz.mendel.uni.controllers.exceptions.ExceptionHandlerController
import cz.mendel.uni.entities.Gender
import cz.mendel.uni.services.GenderService
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
class GenderControllerTest extends Specification {
    @Autowired
    private MockMvc mockMvc
    private Gender gender = new Gender()
    private GenderService genderService
    private GenderController genderController

    def setup() {
        genderService = Mock()
        genderController = new GenderController(genderService)
        mockMvc = MockMvcBuilders.standaloneSetup(genderController).setControllerAdvice(new ExceptionHandlerController()).build()
    }

    def "Status is OK and model has attribute Gender and view returned for /gender/{id}"() {
        given:
        mockMvc = MockMvcBuilders.standaloneSetup(genderController).setControllerAdvice(new ExceptionHandlerController()).build();
        genderService.findById(1) >> gender
        expect: "status is ok"
        mockMvc.perform(MockMvcRequestBuilders.get("/gender/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("gender", gender))
                .andExpect(MockMvcResultMatchers.view().name("genders/gender-info"))
    }

    def "GenderService is used in /gender{id}"() {
        when:
        mockMvc.perform(MockMvcRequestBuilders.get("/gender/1"))
        then:
        1 * genderService.findById(1);
    }

    def "Status is OK and model has attribute Gender and view returned for /gender/add/new"() {
        given:
        mockMvc = MockMvcBuilders.standaloneSetup(genderController).setControllerAdvice(new ExceptionHandlerController()).build();
        expect: "status is ok"
        mockMvc.perform(MockMvcRequestBuilders.get("/gender/add/new"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("gender", gender))
                .andExpect(MockMvcResultMatchers.view().name("genders/add-gender"))
    }

    def "GenderService is used in /gender/add"() {
        when:
        mockMvc.perform(MockMvcRequestBuilders.post("/gender/add"))
        then:
        1 * genderService.save(*_);
    }

    def "Status is OK and model has attribute Gender and view returned for /gender/edit/{id}"() {
        given:
        mockMvc = MockMvcBuilders.standaloneSetup(genderController).setControllerAdvice(new ExceptionHandlerController()).build();
        genderService.findById(1) >> gender
        expect: "status is ok"
        mockMvc.perform(MockMvcRequestBuilders.get("/gender/edit/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("gender", gender))
                .andExpect(MockMvcResultMatchers.view().name("genders/edit-gender"))
    }

    def "GenderService is used in /gender/edit/{id}"() {
        when:
        mockMvc.perform(MockMvcRequestBuilders.get("/gender/edit/1"))
        then:
        1 * genderService.findById(1);
    }

    def "GenderService is used in /gender/edit"() {
        when:
        mockMvc.perform(MockMvcRequestBuilders.post("/gender/edit"))
        then:
        1 * genderService.update(*_);
    }

    def "GenderService is used in /gender/delete/{id}"() {
        when:
        mockMvc.perform(MockMvcRequestBuilders.get("/gender/delete/1"))
        then:
        1 * genderService.deleteById(*_);
    }
}
