package cz.mendel.uni.controllers

import cz.mendel.uni.controllers.exceptions.ExceptionHandlerController
import cz.mendel.uni.entities.Gender
import cz.mendel.uni.entities.Student
import cz.mendel.uni.services.StudentService
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
class StudentControllerTest extends Specification {
    @Autowired
    private MockMvc mockMvc
    private Student student = new Student()
    private StudentService studentService
    private StudentController studentController

    def setup() {
        studentService = Mock()
        studentController = new StudentController(studentService)
        mockMvc = MockMvcBuilders.standaloneSetup(studentController).setControllerAdvice(new ExceptionHandlerController()).build()
    }

    def "Status is OK and model has attribute Student and view returned for /students/{id}"() {
        given:
        mockMvc = MockMvcBuilders.standaloneSetup(studentController).setControllerAdvice(new ExceptionHandlerController()).build()
        studentService.findById(1) >> student
        expect: "status is ok"
        mockMvc.perform(MockMvcRequestBuilders.get("/students/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("student", student))
                .andExpect(MockMvcResultMatchers.view().name("students/student-info"))
    }

    def "StudentService is used in /students/{id}"() {
        when:
        mockMvc.perform(MockMvcRequestBuilders.get("/students/1"))
        then:
        1 * studentService.findById(1)
    }

    def "Status is OK and model has attribute Student and view returned for /students/add/new"() {
        given:
        mockMvc = MockMvcBuilders.standaloneSetup(studentController).setControllerAdvice(new ExceptionHandlerController()).build()
        expect: "status is ok"
        mockMvc.perform(MockMvcRequestBuilders.get("/students/add/new"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("student", Student.builder().gender(new Gender()).build()))
                .andExpect(MockMvcResultMatchers.view().name("students/add-student"))
    }

    def "StudentService is used in /students/add"() {
        when:
        mockMvc.perform(MockMvcRequestBuilders.post("/students/add"))
        then:
        1 * studentService.save(*_)
    }

    def "Status is OK and model has attribute Student and view returned for /students/edit/{id}"() {
        given:
        mockMvc = MockMvcBuilders.standaloneSetup(studentController).setControllerAdvice(new ExceptionHandlerController()).build()
        studentService.findById(1) >> student
        expect: "status is ok"
        mockMvc.perform(MockMvcRequestBuilders.get("/students/edit/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("student", student))
                .andExpect(MockMvcResultMatchers.view().name("students/edit-student"))
    }

    def "StudentService is used in /students/edit/{id}"() {
        when:
        mockMvc.perform(MockMvcRequestBuilders.get("/students/edit/1"))
        then:
        1 * studentService.findById(1)
    }

    def "StudentService is used in /students/edit"() {
        when:
        mockMvc.perform(MockMvcRequestBuilders.post("/students/edit"))
        then:
        1 * studentService.update(*_)
    }

    def "StudentService is used in /students/delete/{id}"() {
        when:
        mockMvc.perform(MockMvcRequestBuilders.get("/students/delete/1"))
        then:
        1 * studentService.deleteById(*_)
    }

    def "Status is OK and model has attribute Students and view returned for /students/list"() {
        given:
        mockMvc = MockMvcBuilders.standaloneSetup(studentController).setControllerAdvice(new ExceptionHandlerController()).build()
        studentService.findAll() >> new ArrayList<>()
        expect: "status is ok"
        mockMvc.perform(MockMvcRequestBuilders.get("/students/list"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("students", new ArrayList<>()))
                .andExpect(MockMvcResultMatchers.view().name("students/get-students"))
    }

    def "StudentService is used in /students/list"() {
        when:
        mockMvc.perform(MockMvcRequestBuilders.get("/students/list"))
        then:
        1 * studentService.findAll()
    }
}
