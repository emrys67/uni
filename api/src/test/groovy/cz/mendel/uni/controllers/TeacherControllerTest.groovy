package cz.mendel.uni.controllers

import cz.mendel.uni.controllers.exceptions.ExceptionHandlerController
import cz.mendel.uni.entities.Gender
import cz.mendel.uni.entities.Teacher
import cz.mendel.uni.entities.TimePeriod
import cz.mendel.uni.entities.Vacation
import cz.mendel.uni.services.TeacherService
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
class TeacherControllerTest extends Specification {
    @Autowired
    private MockMvc mockMvc
    private Teacher teacher = new Teacher()
    private TimePeriodService timePeriodService;
    private VacationService vacationService;
    private TeacherService teacherService;
    private TeacherController teacherController

    def setup() {
        timePeriodService = Mock()
        vacationService = Mock()
        teacherService = Mock()
        teacherController = new TeacherController(timePeriodService, vacationService, teacherService)
        mockMvc = MockMvcBuilders.standaloneSetup(teacherController).setControllerAdvice(new ExceptionHandlerController()).build()
    }

    def "Status is OK and model has attribute Teacher and view returned for /teachers/{id}"() {
        given:
        mockMvc = MockMvcBuilders.standaloneSetup(teacherController).setControllerAdvice(new ExceptionHandlerController()).build();
        teacherService.findById(1) >> teacher
        expect: "status is ok"
        mockMvc.perform(MockMvcRequestBuilders.get("/teachers/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("teacher", teacher))
                .andExpect(MockMvcResultMatchers.view().name("teachers/teacher-info"))
    }

    def "TeacherService is used in /teachers/{id}"() {
        when:
        mockMvc.perform(MockMvcRequestBuilders.get("/teachers/1"))
        then:
        1 * teacherService.findById(1);
    }

    def "Status is OK and model has attribute Teacher and view returned for /teachers/add/new"() {
        given:
        mockMvc = MockMvcBuilders.standaloneSetup(teacherController).setControllerAdvice(new ExceptionHandlerController()).build();
        expect: "status is ok"
        mockMvc.perform(MockMvcRequestBuilders.get("/teachers/add/new"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("teacher", Teacher.builder()
                        .vacation(new Vacation()).workingHours(new TimePeriod()).gender(new Gender()).build()))
                .andExpect(MockMvcResultMatchers.view().name("teachers/add-teacher"))
    }

    def "StudentService, TeacherService and VacationService are used in /teachers/add"() {
        when:
        mockMvc.perform(MockMvcRequestBuilders.post("/teachers/add").flashAttr("teacher", Teacher.builder()
                .vacation(new Vacation()).workingHours(new TimePeriod()).gender(new Gender()).build()))
        then:
        1 * teacherService.save(_)
        1 * vacationService.save(_)
        2 * timePeriodService.save(_)
    }

    def "Status is OK and model has attribute Teacher and view returned for /teachers/edit/{id}"() {
        given:
        mockMvc = MockMvcBuilders.standaloneSetup(teacherController).setControllerAdvice(new ExceptionHandlerController()).build();
        teacherService.findById(1) >> teacher
        expect: "status is ok"
        mockMvc.perform(MockMvcRequestBuilders.get("/teachers/edit/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("teacher", teacher))
                .andExpect(MockMvcResultMatchers.view().name("teachers/edit-teacher"))
    }

    def "TeacherService is used in /teachers/edit/{id}"() {
        when:
        mockMvc.perform(MockMvcRequestBuilders.get("/teachers/edit/1"))
        then:
        1 * teacherService.findById(1);
    }

    def "TeacherService is used in /teachers/edit"() {
        when:
        mockMvc.perform(MockMvcRequestBuilders.post("/teachers/edit"))
        then:
        1 * teacherService.update(*_);
    }

    def "TeacherService is used in /teachers/delete/{id}"() {
        when:
        mockMvc.perform(MockMvcRequestBuilders.get("/teachers/delete/1"))
        then:
        1 * teacherService.deleteById(*_);
    }

    def "Status is OK and model has attribute Teachers and view returned for /teachers/list"() {
        given:
        mockMvc = MockMvcBuilders.standaloneSetup(teacherController).setControllerAdvice(new ExceptionHandlerController()).build();
        teacherService.findAll() >> new ArrayList<>()
        expect: "status is ok"
        mockMvc.perform(MockMvcRequestBuilders.get("/teachers/list"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("teachers", new ArrayList<>()))
                .andExpect(MockMvcResultMatchers.view().name("teachers/get-teachers"))
    }

    def "TeacherService is used in /teachers/list"() {
        when:
        mockMvc.perform(MockMvcRequestBuilders.get("/teachers/list"))
        then:
        1 * teacherService.findAll()
    }
}
