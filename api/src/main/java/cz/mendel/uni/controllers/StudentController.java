package cz.mendel.uni.controllers;

import cz.mendel.uni.entities.Gender;
import cz.mendel.uni.entities.Student;
import cz.mendel.uni.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SessionAttributes("student")
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/list")
    public String students(Model model) {
        List<Student> students = studentService.findAll();
        model.addAttribute("students", students);
        return "students/get-students";
    }

    @GetMapping("/{id}")
    public String studentInfo(@PathVariable("id") long id, Model model) {
        Student student = studentService.findById(id);
        model.addAttribute("student", student);
        return "students/student-info";
    }

    @GetMapping("/add")
    public String addStudent(Model model) {
        Student student = new Student();
        student.setGender(new Gender());
        model.addAttribute("student", student);
        return "students/add-student";
    }

    @PostMapping("/add/new")
    public String addNewStudent(@ModelAttribute Student student) {
        studentService.save(student);
        return "redirect:/students/students";
    }

    @GetMapping("/edit/{id}")
    public String editStudent(@PathVariable("id") long id, Model model) {
        Student student = studentService.findById(id);
        model.addAttribute("student", student);
        return "students/edit-student";
    }

    @PostMapping("/edit")
    public String editStudent(@ModelAttribute Student student) {
        studentService.update(student);
        return "redirect:/students/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") long id) {
        studentService.deleteById(id);
        return "redirect:/students/list";
    }
}
