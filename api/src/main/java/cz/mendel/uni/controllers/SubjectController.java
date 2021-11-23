package cz.mendel.uni.controllers;

import cz.mendel.uni.entities.Subject;
import cz.mendel.uni.entities.Teacher;
import cz.mendel.uni.services.SubjectService;
import cz.mendel.uni.services.TeacherService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@SessionAttributes({"subject", "teacher"})
@RequestMapping("/subjects")
@AllArgsConstructor
public class SubjectController {
    private SubjectService subjectService;
    private TeacherService teacherService;

    @ApiOperation(value = "Get all subjects")
    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("subjects", subjectService.findAll());
        return "subjects/get-subjects";
    }

    @ApiOperation(value = "Get subject by id")
    @GetMapping("/{id}")
    public String subjectInfo(@PathVariable("id") long id, Model model) {
        model.addAttribute("subject", subjectService.findById(id));
        return "subjects/subject-info";
    }

    @ApiOperation(value = "Create new subject")
    @GetMapping("/add/new")
    public String createSubject(Model model) {
        model.addAttribute("teachers", teacherService.findAll());
        model.addAttribute("subject", new Subject());
        return "subjects/add-subject";
    }

    @ApiOperation(value = "Create new subject")
    @ApiIgnore
    @PostMapping("/add")
    public String addSubject(Subject subject) {
        subjectService.save(subject);
        return "redirect:/subjects/add/new";
    }

    @ApiOperation(value = "Edit subject by id")
    @GetMapping("/edit/{id}")
    public String editSubject(@PathVariable("id") long id, Model model) {
        model.addAttribute("teachers", teacherService.findAll());
        model.addAttribute("subject", subjectService.findById(id));
        return "subjects/edit-subject";
    }

    @ApiOperation(value = "Edit subject")
    @ApiIgnore
    @PostMapping("/edit")
    public String edit(Subject subject) {
        subjectService.update(subject);
        return "redirect:/subjects/list";
    }

    @ApiOperation(value = "Delete subject by id")
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        subjectService.deleteById(id);
        return "redirect:/subjects/list";
    }

    @ApiOperation(value = "Add teacher to subject")
    @ApiIgnore
    @PostMapping("/add/teacher/{subjectId}")
    public String addTeacher(Teacher teacher, @PathVariable("subjectId") long subjectId) {
        Subject subject = subjectService.findById(subjectId);
        subjectService.addTeacher(subject, teacherService.findById(teacher.getId()));
        subjectService.update(subject);
        return String.format("redirect:/subjects/%s", subjectId);
    }

    @ApiOperation(value = "Add teacher to subject")
    @GetMapping("/add-teacher/{id}")
    public String saveTeacher(@PathVariable("id") long id, Model model) {
        model.addAttribute("subjectId", id);
        model.addAttribute("teacher", new Teacher());
        return "subjects/add-teacher";
    }
}
