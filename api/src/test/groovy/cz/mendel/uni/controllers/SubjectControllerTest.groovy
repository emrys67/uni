package cz.mendel.uni.controllers

import cz.mendel.uni.controllers.exceptions.ExceptionHandlerController
import cz.mendel.uni.entities.Subject
import cz.mendel.uni.entities.Teacher
import cz.mendel.uni.services.SubjectService
import cz.mendel.uni.services.TeacherService
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
class SubjectControllerTest extends Specification {
    @Autowired
    private MockMvc mockMvc
    private Subject subject = new Subject()
    private SubjectService subjectService
    private TeacherService teacherService
    private SubjectController subjectController

    def setup() {
        subjectService = Mock()
        teacherService = Mock()
        subjectController = new SubjectController(subjectService, teacherService)
        mockMvc = MockMvcBuilders.standaloneSetup(subjectController).setControllerAdvice(new ExceptionHandlerController()).build()
    }

    def "Status is OK and model has attribute Subject and view returned for /students/{id}"() {
        given:
        mockMvc = MockMvcBuilders.standaloneSetup(subjectController).setControllerAdvice(new ExceptionHandlerController()).build()
        subjectService.findById(1) >> subject
        expect: "status is ok"
        mockMvc.perform(MockMvcRequestBuilders.get("/subjects/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("subject", subject))
                .andExpect(MockMvcResultMatchers.view().name("subjects/subject-info"))
    }

    def "SubjectService is used in /subjects/{id}"() {
        when:
        mockMvc.perform(MockMvcRequestBuilders.get("/subjects/1"))
        then:
        1 * subjectService.findById(1)
    }

    def "Status is OK and model has attribute Subject and view returned for /subjects/add/new"() {
        given:
        mockMvc = MockMvcBuilders.standaloneSetup(subjectController).setControllerAdvice(new ExceptionHandlerController()).build()
        teacherService.findAll() >> new ArrayList<Teacher>()
        expect: "status is ok"
        mockMvc.perform(MockMvcRequestBuilders.get("/subjects/add/new"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("subject", subject))
                .andExpect(MockMvcResultMatchers.model().attribute("teachers", new ArrayList<Teacher>()))
                .andExpect(MockMvcResultMatchers.view().name("subjects/add-subject"))
    }

    def "SubjectService is used in /subjects/add"() {
        when:
        mockMvc.perform(MockMvcRequestBuilders.post("/subjects/add"))
        then:
        1 * subjectService.save(*_)
    }

    def "Status is OK and model has attribute Subject and view returned for /subjects/edit/{id}"() {
        given:
        mockMvc = MockMvcBuilders.standaloneSetup(subjectController).setControllerAdvice(new ExceptionHandlerController()).build()
        subjectService.findById(1) >> subject
        expect: "status is ok"
        mockMvc.perform(MockMvcRequestBuilders.get("/subjects/edit/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("subject", subject))
                .andExpect(MockMvcResultMatchers.view().name("subjects/edit-subject"))
    }

    def "Status is OK and model has attribute Subjects and view returned for /subjects/list"() {
        given:
        mockMvc = MockMvcBuilders.standaloneSetup(subjectController).setControllerAdvice(new ExceptionHandlerController()).build()
        subjectService.findAll() >> new ArrayList<Subject>()
        expect: "status is ok"
        mockMvc.perform(MockMvcRequestBuilders.get("/subjects/list"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("subjects", new ArrayList<Subject>()))
                .andExpect(MockMvcResultMatchers.view().name("subjects/get-subjects"))
    }

    def "SubjectService is used in /subjects/list"() {
        when:
        mockMvc.perform(MockMvcRequestBuilders.get("/subjects/list"))
        then:
        1 * subjectService.findAll()
    }

    def "SubjectService is used in /subjects/edit/{id}"() {
        when:
        mockMvc.perform(MockMvcRequestBuilders.get("/subjects/edit/1"))
        then:
        1 * subjectService.findById(1)
    }

    def "SubjectService is used in /subjects/edit"() {
        when:
        mockMvc.perform(MockMvcRequestBuilders.post("/subjects/edit"))
        then:
        1 * subjectService.update(*_)
    }

    def "SubjectService is used in /subjects/delete/{id}"() {
        when:
        mockMvc.perform(MockMvcRequestBuilders.get("/subjects/delete/1"))
        then:
        1 * subjectService.deleteById(*_)
    }

    def "TeacherService is used in /subjects/add/new"() {
        when:
        mockMvc.perform(MockMvcRequestBuilders.get("/subjects/add/new"))
        then:
        1 * teacherService.findAll()
    }

    def "Status is OK and model has attributes teacher and subjectId and view returned for /subjects/add-teacher/{id}"() {
        given:
        mockMvc = MockMvcBuilders.standaloneSetup(subjectController).setControllerAdvice(new ExceptionHandlerController()).build()
        expect: "status is ok"
        mockMvc.perform(MockMvcRequestBuilders.get("/subjects/add-teacher/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("subjectId", (long) 1))
                .andExpect(MockMvcResultMatchers.model().attribute("teacher", new Teacher()))
                .andExpect(MockMvcResultMatchers.view().name("subjects/add-teacher"))
    }

    def "SubjectService and TeacherService are used in /subjects/add/teacher/{subjectId}"() {
        when:
        mockMvc.perform(MockMvcRequestBuilders.post("/subjects/add/teacher/1"))
        then:
        1 * teacherService.findById(_)
        1 * subjectService.findById(_)
        1 * subjectService.addTeacher(_, _)
        1 * subjectService.update(_)
    }
}
