package cz.mendel.uni.controllers;

import cz.mendel.uni.entities.Group;
import cz.mendel.uni.entities.Student;
import cz.mendel.uni.services.GroupService;
import cz.mendel.uni.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.lang.String.format;

@SessionAttributes("group")
@RequestMapping("/groups")
public class GroupController {
    @Autowired
    private GroupService groupService;
    @Autowired
    private StudentService studentService;

    @GetMapping("/list")
    public String home(Model model) {
        List<Group> list = groupService.findAll();
        model.addAttribute("groups", list);
        return "groups/get-groups";
    }

    @GetMapping("/{id}")
    public String groupInfo(@PathVariable("id") long id, Model model) {
        Group group = groupService.findById(id);
        model.addAttribute("group", group);
        return "groups/group-info";
    }

    @GetMapping("/delete/{id}")
    public String deleteGroup(@PathVariable("id") long id) {
        groupService.deleteById(id);
        return "redirect:/groups/list";
    }

    @GetMapping("/edit/{id}")
    public String editGroup(@PathVariable("id") long id, Model model) {
        Group group = groupService.findById(id);
        model.addAttribute("group", group);
        return "/groups/edit-group";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Group group) {
        groupService.save(group);
        return "redirect:/groups/list";
    }

    @GetMapping("/add")
    public String addGroup(Model model) {
        Group group = new Group();
        model.addAttribute("group", group);
        return "groups/add-group";
    }

    @PostMapping("/add/new")
    public String add(@ModelAttribute Group group) {
        groupService.save(new Group().builder().name(group.getName()).build());
        return "redirect:/groups/add";
    }

    @PostMapping("/add/student/{groupId}")
    public String addStudent(@ModelAttribute Student student, @PathVariable("groupId") long groupId) {
        student = studentService.findById(student.getId());
        Group group = groupService.findById(groupId);
        groupService.addStudent(student, group);
        return format("redirect:/groups/add-student/%s", groupId);
    }

    @GetMapping("/add-student/{id}")
    public String saveStudent(@PathVariable("id") long id, Model model) {
        Student student = new Student();
        model.addAttribute("groupId", id);
        model.addAttribute("student", student);
        return "groups/add-student";
    }
}
