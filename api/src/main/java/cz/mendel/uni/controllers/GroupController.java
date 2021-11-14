package cz.mendel.uni.controllers;

import cz.mendel.uni.entities.Group;
import cz.mendel.uni.entities.Student;
import cz.mendel.uni.services.GroupService;
import cz.mendel.uni.services.StudentService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

import static java.lang.String.format;

@SessionAttributes("group")
@RequestMapping("/groups")
@AllArgsConstructor
public class GroupController {
    private GroupService groupService;
    private StudentService studentService;

    @ApiOperation(value = "Get all groups")
    @GetMapping("/list")
    public String home(Model model) {
        List<Group> list = groupService.findAll();
        model.addAttribute("groups", list);
        return "groups/get-groups";
    }

    @ApiOperation(value = "Get group by id")
    @GetMapping("/{id}")
    public String groupInfo(@PathVariable("id") long id, Model model) {
        Group group = groupService.findById(id);
        model.addAttribute("group", group);
        return "groups/group-info";
    }

    @ApiOperation(value = "Delete group by id")
    @GetMapping("/delete/{id}")
    public String deleteGroup(@PathVariable("id") long id) {
        groupService.deleteById(id);
        return "redirect:/groups/list";
    }

    @ApiOperation(value = "Edit group by id")
    @GetMapping("/edit/{id}")
    public String editGroup(@PathVariable("id") long id, Model model) {
        Group group = groupService.findById(id);
        model.addAttribute("group", group);
        return "groups/edit-group";
    }

    @ApiOperation(value = "Edit group")
    @ApiIgnore
    @PostMapping("/edit")
    public String edit(Group group) {
        groupService.update(group);
        return "redirect:/groups/list";
    }

    @ApiOperation(value = "Create new group")
    @GetMapping("/add/new")
    public String addGroup(Model model) {
        Group group = new Group();
        model.addAttribute("group", group);
        return "groups/add-group";
    }

    @ApiOperation(value = "Create new group")
    @ApiIgnore
    @PostMapping("/add")
    public String add( Group group) {
        groupService.save(new Group().builder().name(group.getName()).build());
        return "redirect:/groups/add/new";
    }

    @ApiOperation(value = "Add student to the group")
    @ApiIgnore
    @PostMapping("/add/student/{groupId}")
    public String addStudent(Student student, @PathVariable("groupId") long groupId) {
        student = studentService.findById(student.getId());
        Group group = groupService.findById(groupId);
        groupService.addStudent(student, group);
        groupService.update(group);
        return format("redirect:/groups/add-student/%s", groupId);
    }

    @ApiOperation(value = "Add student to the group")
    @GetMapping("/add-student/{id}")
    public String saveStudent(@PathVariable("id") long id, Model model) {
        Student student = new Student();
        model.addAttribute("groupId", id);
        model.addAttribute("student", student);
        return "groups/add-student";
    }
}
