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

import java.util.List;

import static java.lang.String.format;

@SessionAttributes({"subject", "teacher"})
@RequestMapping("/subjects")
@AllArgsConstructor
public class SubjectController {
    private SubjectService subjectService;
    private TeacherService teacherService;

    @ApiOperation(value = "Get all subjects")
    @GetMapping("/list")
    public String list(Model model) {
        List<Subject> subjects = subjectService.findAll();
        model.addAttribute("subjects", subjects);
        return "subjects/get-subjects";
    }

    @ApiOperation(value = "Get subject by id")
    @GetMapping("/{id}")
    public String subjectInfo(@PathVariable("id") long id, Model model) {
        Subject subject = subjectService.findById(id);
        model.addAttribute("subject", subject);
        return "subjects/subject-info";
    }

    @ApiOperation(value = "Create new subject")
    @GetMapping("/add/new")
    public String createSubject(Model model) {
        Subject subject = new Subject();
        List<Teacher> teachers = teacherService.findAll();
        model.addAttribute("teachers", teachers);
        model.addAttribute("subject", subject);
        return "subjects/add-subject";
    }

    @ApiOperation(value = "Create new subject")
    @ApiIgnore
    @PostMapping("/add")
    public String addSubject(Subject subject) {
        subjectService.save(subject);
//        subjectService.addTeacher(subject, subject.getSupervisor());
        return "redirect:/subjects/add/new";
    }

    @ApiOperation(value = "Edit subject by id")
    @GetMapping("/edit/{id}")
    public String editSubject(@PathVariable("id") long id, Model model) {
        Subject subject = subjectService.findById(id);
        List<Teacher> teachers = teacherService.findAll();
        model.addAttribute("teachers", teachers);
        model.addAttribute("subject", subject);
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
        teacher = teacherService.findById(teacher.getId());
        Subject subject = subjectService.findById(subjectId);
        subjectService.addTeacher(subject, teacher);
        subjectService.update(subject);
        return format("redirect:/subjects/%s", subjectId);
    }

    @ApiOperation(value = "Add teacher to subject")
    @GetMapping("/add-teacher/{id}")
    public String saveTeacher(@PathVariable("id") long id, Model model) {
        Teacher teacher = new Teacher();
        model.addAttribute("subjectId", id);
        model.addAttribute("teacher", teacher);
        return "subjects/add-teacher";
    }
}
