package cz.mendel.uni.controllers;

import cz.mendel.uni.entities.Gender;
import cz.mendel.uni.services.GenderService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static java.lang.String.format;

@SessionAttributes("gender")
@RequestMapping("/gender")
@AllArgsConstructor
public class GenderController {
    private GenderService genderService;

    @ApiOperation(value = "Get gender by id")
    @GetMapping("/{id}")
    public String genderInfo(@PathVariable("id") long id, Model model) {
        Gender gender = genderService.findById(id);
        model.addAttribute("gender", gender);
        return "genders/gender-info";
    }

    @ApiOperation(value = "Edit gender by id")
    @GetMapping("/edit/{id}")
    public String editGender(@PathVariable("id") long id, Model model) {
        Gender gender = genderService.findById(id);
        model.addAttribute("gender", gender);
        return "genders/edit-gender";
    }

    @ApiOperation(value = "Edit gender")
    @PostMapping("/edit")
    public String edit(Gender gender) {
        genderService.update(gender);
        return format("redirect:/gender/%s", gender.getId());
    }

    @ApiOperation(value = "Create new gender")
    @GetMapping("/add/new")
    public String addGender(Model model) {
        Gender gender = new Gender();
        model.addAttribute("gender", gender);
        return "genders/add-gender";
    }

    @ApiOperation(value = "Create new gender")
    @PostMapping("/add")
    public String add(Gender gender) {
        genderService.save(gender);
        return "redirect:/timetable/list";
    }

    @ApiOperation(value = "Delete gender by id")
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        genderService.deleteById(id);
        return "redirect:/timetable/list";
    }
}
