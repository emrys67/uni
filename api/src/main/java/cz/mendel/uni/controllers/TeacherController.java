package cz.mendel.uni.controllers;

import cz.mendel.uni.entities.Gender;
import cz.mendel.uni.entities.Teacher;
import cz.mendel.uni.entities.TimePeriod;
import cz.mendel.uni.entities.Vacation;
import cz.mendel.uni.services.TeacherService;
import cz.mendel.uni.services.TimePeriodService;
import cz.mendel.uni.services.VacationService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@SessionAttributes("teacher")
@RequestMapping("/teachers")
@AllArgsConstructor
public class TeacherController {
    private TimePeriodService timePeriodService;
    private VacationService vacationService;
    private TeacherService teacherService;

    @ApiOperation(value = "Get all teachers")
    @GetMapping("/list")
    public String teachers(Model model) {
        List<Teacher> teachers = teacherService.findAll();
        model.addAttribute("teachers", teachers);
        return "teachers/get-teachers";
    }

    @ApiOperation(value = "Get teacher by id")
    @GetMapping("/{id}")
    public String teacherInfo(@PathVariable("id") long id, Model model) {
        Teacher teacher = teacherService.findById(id);
        model.addAttribute("teacher", teacher);
        return "teachers/teacher-info";
    }

    @ApiOperation(value = "Create new teacher")
    @GetMapping("/add/new")
    public String addTeacher(Model model) {
        Teacher teacher = new Teacher();
        teacher.setGender(new Gender());
        teacher.setVacation(new Vacation());
        teacher.setWorkingHours(new TimePeriod());
        model.addAttribute("teacher", teacher);
        return "teachers/add-teacher";
    }

    @ApiOperation(value = "Create new teacher")
    @ApiIgnore
    @PostMapping("/add")
    public String addNewTeacher(Teacher teacher) {
        timePeriodService.save(teacher.getWorkingHours());
        timePeriodService.save(teacher.getVacation().getTimePeriod());
        vacationService.save(teacher.getVacation());
        teacherService.save(teacher);
        return "redirect:/teachers/list";
    }

    @ApiOperation(value = "Edit teacher by id")
    @GetMapping("/edit/{id}")
    public String editTeacher(@PathVariable("id") long id, Model model) {
        Teacher teacher = teacherService.findById(id);
        model.addAttribute("teacher", teacher);
        return "teachers/edit-teacher";
    }

    @ApiOperation(value = "Edit teacher by id")
    @ApiIgnore
    @PostMapping("/edit")
    public String editTeacher(Teacher teacher) {
        teacherService.update(teacher);
        return "redirect:/teachers/list";
    }

    @ApiOperation(value = "Delete teacher by id")
    @GetMapping("/delete/{id}")
    public String deleteTeacher(@PathVariable("id") long id) {
        teacherService.deleteById(id);
        return "redirect:/teachers/list";
    }
}
