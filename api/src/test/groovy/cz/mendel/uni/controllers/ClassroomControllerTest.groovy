package cz.mendel.uni.controllers

import cz.mendel.uni.controllers.exceptions.ExceptionHandlerController
import cz.mendel.uni.entities.Classroom
import cz.mendel.uni.services.ClassroomService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

@SpringBootTest
@AutoConfigureMockMvc
@EnableAutoConfiguration
class ClassroomControllerTest extends Specification {
    @Autowired
    private MockMvc mockMvc;
    private Classroom classroom = new Classroom();
    private ClassroomService classroomService;
    private ClassroomController classroomController;

    def setup() {
        classroomService = Mock();
        classroomController = new ClassroomController(classroomService);
        mockMvc = MockMvcBuilders.standaloneSetup(classroomController).setControllerAdvice(new ExceptionHandlerController()).build();
    }

    def "Status is OK and model has attribute Classroom and view returned for /classroom/{id}"() {
        given:
        mockMvc = MockMvcBuilders.standaloneSetup(classroomController).setControllerAdvice(new ExceptionHandlerController()).build();
        classroomService.findById(1) >> classroom
        expect: "status is ok"
        mockMvc.perform(MockMvcRequestBuilders.get("/classroom/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("classroom", classroom))
                .andExpect(MockMvcResultMatchers.view().name("classrooms/classroom-info"))
    }

    def "ClassroomService is used in /classroom{id}"() {
        when:
        mockMvc.perform(MockMvcRequestBuilders.get("/classroom/1"))
        then:
        1 * classroomService.findById(1);
    }

    def "Status is OK and model has attribute Classroom and view returned for /classroom/add/new"() {
        given:
        mockMvc = MockMvcBuilders.standaloneSetup(classroomController).setControllerAdvice(new ExceptionHandlerController()).build();
        expect: "status is ok"
        mockMvc.perform(MockMvcRequestBuilders.get("/classroom/add/new"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("classroom", classroom))
                .andExpect(MockMvcResultMatchers.view().name("classrooms/add-classroom"))
    }

    def "ClassroomService is used in /classroom/add"() {
        when:
        mockMvc.perform(MockMvcRequestBuilders.post("/classroom/add"))
        then:
        1 * classroomService.save(*_);
    }

    def "Status is OK and model has attribute Classroom and view returned for /classroom/edit/{id}"() {
        given:
        mockMvc = MockMvcBuilders.standaloneSetup(classroomController).setControllerAdvice(new ExceptionHandlerController()).build();
        classroomService.findById(1) >> classroom
        expect: "status is ok"
        mockMvc.perform(MockMvcRequestBuilders.get("/classroom/edit/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("classroom", classroom))
                .andExpect(MockMvcResultMatchers.view().name("classrooms/edit-classroom"))
    }

    def "ClassroomService is used in /classroom/edit/{id}"() {
        when:
        mockMvc.perform(MockMvcRequestBuilders.get("/classroom/edit/1"))
        then:
        1 * classroomService.findById(1);
    }

    def "ClassroomService is used in /classroom/edit"() {
        when:
        mockMvc.perform(MockMvcRequestBuilders.post("/classroom/edit"))
        then:
        1 * classroomService.update(*_);
    }

    def "ClassroomService is used in /classroom/delete/{id}"() {
        when:
        mockMvc.perform(MockMvcRequestBuilders.get("/classroom/delete/1"))
        then:
        1 * classroomService.deleteById(*_);
    }
}
