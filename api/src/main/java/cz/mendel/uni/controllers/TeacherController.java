package cz.mendel.uni.controllers;

import cz.mendel.uni.entities.Gender;
import cz.mendel.uni.entities.Teacher;
import cz.mendel.uni.entities.TimePeriod;
import cz.mendel.uni.entities.Vacation;
import cz.mendel.uni.services.TeacherService;
import cz.mendel.uni.services.TimePeriodService;
import cz.mendel.uni.services.VacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SessionAttributes("teacher")
@RequestMapping("/teachers")
public class TeacherController {
    @Autowired
    private TimePeriodService timePeriodService;
    @Autowired
    private VacationService vacationService;
    @Autowired
    private TeacherService teacherService;

    @GetMapping("/list")
    public String teachers(Model model) {
        List<Teacher> teachers = teacherService.findAll();
        model.addAttribute("teachers", teachers);
        return "teachers/get-teachers";
    }

    @GetMapping("/{id}")
    public String teacherInfo(@PathVariable("id") long id, Model model) {
        Teacher teacher = teacherService.findById(id);
        model.addAttribute("teacher", teacher);
        return "teachers/teacher-info";
    }

    @GetMapping("/add")
    public String addTeacher(Model model) {
        Teacher teacher = new Teacher();
        teacher.setGender(new Gender());
        teacher.setVacation(new Vacation());
        teacher.setWorkingHours(new TimePeriod());
        model.addAttribute("teacher", teacher);
        return "teachers/add-teacher";
    }

    @PostMapping("/add/new")
    public String addNewTeacher(@ModelAttribute Teacher teacher) {
        timePeriodService.save(teacher.getWorkingHours());
        timePeriodService.save(teacher.getVacation().getTimePeriod());
        vacationService.save(teacher.getVacation());
        teacherService.save(teacher);
        return "redirect:/teachers/list";
    }

    @GetMapping("/edit/{id}")
    public String editTeacher(@PathVariable("id") long id, Model model) {
        Teacher teacher = teacherService.findById(id);
        model.addAttribute("teacher", teacher);
        return "teachers/edit-teacher";
    }

    @PostMapping("/edit")
    public String editTeacher(@ModelAttribute Teacher teacher) {
        teacherService.update(teacher);
        return "redirect:/teachers/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteTeacher(@PathVariable("id") long id) {
        teacherService.deleteById(id);
        return "redirect:/teachers/list";
    }
}
