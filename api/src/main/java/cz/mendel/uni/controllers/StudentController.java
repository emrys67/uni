package cz.mendel.uni.controllers;

import cz.mendel.uni.entities.Gender;
import cz.mendel.uni.entities.Student;
import cz.mendel.uni.services.StudentService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@SessionAttributes("student")
@RequestMapping("/students")
@AllArgsConstructor
public class StudentController {
    private StudentService studentService;

    @ApiOperation(value = "Get all students")
    @GetMapping("/list")
    public String students(Model model) {
        model.addAttribute("students", studentService.findAll());
        return "students/get-students";
    }

    @ApiOperation(value = "Get student by id")
    @GetMapping("/{id}")
    public String studentInfo(@PathVariable("id") long id, Model model) {
        model.addAttribute("student", studentService.findById(id));
        return "students/student-info";
    }

    @ApiOperation(value = "Create new student")
    @GetMapping("/add/new")
    public String addStudent(Model model) {
        model.addAttribute("student", Student.builder().gender(new Gender()).build());
        return "students/add-student";
    }

    @ApiOperation(value = "Create new student")
    @ApiIgnore
    @PostMapping("/add")
    public String addNewStudent(Student student) {
        studentService.save(student);
        return "redirect:/students/list";
    }

    @ApiOperation(value = "Edit student by id")
    @GetMapping("/edit/{id}")
    public String editStudent(@PathVariable("id") long id, Model model) {
        model.addAttribute("student", studentService.findById(id));
        return "students/edit-student";
    }

    @ApiOperation(value = "Edit student")
    @ApiIgnore
    @PostMapping("/edit")
    public String editStudent(Student student) {
        studentService.update(student);
        return "redirect:/students/list";
    }

    @ApiOperation(value = "Delete student by id")
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") long id) {
        studentService.deleteById(id);
        return "redirect:/students/list";
    }
}
