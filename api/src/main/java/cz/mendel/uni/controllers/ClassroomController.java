package cz.mendel.uni.controllers;

import cz.mendel.uni.entities.Classroom;
import cz.mendel.uni.services.ClassroomService;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static java.lang.String.format;

@SessionAttributes("classroom")
@RequestMapping("/classroom")
@AllArgsConstructor
public class ClassroomController {
    private ClassroomService classroomService;

    @GetMapping("/{id}")
    public String classroomInfo(@PathVariable("id") long id, Model model) {
        Classroom classroom = classroomService.findById(id);
        model.addAttribute("classroom", classroom);
        return "classrooms/classroom-info";
    }

    @GetMapping("/edit/{id}")
    public String editClassroom(@PathVariable("id") long id, Model model) {
        Classroom classroom = classroomService.findById(id);
        model.addAttribute("classroom", classroom);
        return "classrooms/edit-classroom";
    }

    @PostMapping("/edit")
    public String edit(Classroom classroom) {
        classroomService.update(classroom);
        return format("redirect:/classroom/%s", classroom.getId());
    }

    @GetMapping("/add/new")
    public String addClassroom(Model model) {
        Classroom classroom = new Classroom();
        model.addAttribute("classroom", classroom);
        return "classrooms/add-classroom";
    }

    @PostMapping("/add")
    public String add(Classroom classroom) {
        classroomService.save(classroom);
        return "redirect:/timetable/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        classroomService.deleteById(id);
        return "redirect:/timetable/list";
    }
}
