package cz.mendel.uni.controllers

import cz.mendel.uni.controllers.exceptions.ExceptionHandlerController
import cz.mendel.uni.entities.Group
import cz.mendel.uni.entities.Student
import cz.mendel.uni.services.GroupService
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
class GroupControllerTest extends Specification {
    @Autowired
    private MockMvc mockMvc
    private Group group = new Group()
    private GroupService groupService
    private StudentService studentService;
    private GroupController groupController

    def setup() {
        groupService = Mock()
        studentService = Mock()
        groupController = new GroupController(groupService, studentService)
        mockMvc = MockMvcBuilders.standaloneSetup(groupController).setControllerAdvice(new ExceptionHandlerController()).build()
    }

    def "Status is OK and model has attribute Group and view returned for /groups/{id}"() {
        given:
        mockMvc = MockMvcBuilders.standaloneSetup(groupController).setControllerAdvice(new ExceptionHandlerController()).build();
        groupService.findById(1) >> group
        expect: "status is ok"
        mockMvc.perform(MockMvcRequestBuilders.get("/groups/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("group", group))
                .andExpect(MockMvcResultMatchers.view().name("groups/group-info"))
    }

    def "GroupService is used in /groups{id}"() {
        when:
        mockMvc.perform(MockMvcRequestBuilders.get("/groups/1"))
        then:
        1 * groupService.findById(1);
    }

    def "Status is OK and model has attribute Group and view returned for /groups/add/new"() {
        given:
        mockMvc = MockMvcBuilders.standaloneSetup(groupController).setControllerAdvice(new ExceptionHandlerController()).build();
        expect: "status is ok"
        mockMvc.perform(MockMvcRequestBuilders.get("/groups/add/new"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("group", group))
                .andExpect(MockMvcResultMatchers.view().name("groups/add-group"))
    }

    def "GroupService is used in /groups/add"() {
        when:
        mockMvc.perform(MockMvcRequestBuilders.post("/groups/add"))
        then:
        1 * groupService.save(*_);
    }

    def "Status is OK and model has attribute Group and view returned for /groups/edit/{id}"() {
        given:
        mockMvc = MockMvcBuilders.standaloneSetup(groupController).setControllerAdvice(new ExceptionHandlerController()).build();
        groupService.findById(1) >> group
        expect: "status is ok"
        mockMvc.perform(MockMvcRequestBuilders.get("/groups/edit/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("group", group))
                .andExpect(MockMvcResultMatchers.view().name("groups/edit-group"))
    }

    def "GroupService is used in /groups/edit/{id}"() {
        when:
        mockMvc.perform(MockMvcRequestBuilders.get("/groups/edit/1"))
        then:
        1 * groupService.findById(1);
    }

    def "GroupService is used in /groups/edit"() {
        when:
        mockMvc.perform(MockMvcRequestBuilders.post("/groups/edit"))
        then:
        1 * groupService.update(*_);
    }

    def "GroupService is used in /groups/delete/{id}"() {
        when:
        mockMvc.perform(MockMvcRequestBuilders.get("/groups/delete/1"))
        then:
        1 * groupService.deleteById(*_);
    }

    def "GroupService and StudentService are used in /groups/add/student/{id}"() {
        when:
        mockMvc.perform(MockMvcRequestBuilders.post("/groups/add/student/1"))
        then:
        1 * groupService.findById(1)
        1 * groupService.addStudent(*_)
        1 * studentService.findById(*_)
    }

    def "Status is OK and model has attribute Group and view returned for /groups/add/student/{id}"() {
        given:
        mockMvc = MockMvcBuilders.standaloneSetup(groupController).setControllerAdvice(new ExceptionHandlerController()).build();
        expect: "status is ok"
        mockMvc.perform(MockMvcRequestBuilders.get("/groups/add-student/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("groupId", (long) 1))
                .andExpect(MockMvcResultMatchers.model().attribute("student", new Student()))
                .andExpect(MockMvcResultMatchers.view().name("groups/add-student"))
    }

    def "Status is OK and model has attribute Groups and view returned for /groups/list"() {
        given:
        mockMvc = MockMvcBuilders.standaloneSetup(groupController).setControllerAdvice(new ExceptionHandlerController()).build();
        groupService.findAll() >> new ArrayList<Group>()
        expect: "status is ok"
        mockMvc.perform(MockMvcRequestBuilders.get("/groups/list"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("groups", new ArrayList<Group>()))
                .andExpect(MockMvcResultMatchers.view().name("groups/get-groups"))
    }

    def "GroupService is used in /groups/list"() {
        when:
        mockMvc.perform(MockMvcRequestBuilders.get("/groups/list"))
        then:
        1 * groupService.findAll()
    }
}
