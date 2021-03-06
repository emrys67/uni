package cz.mendel.uni.controllers;

import cz.mendel.uni.entities.TimePeriod;
import cz.mendel.uni.entities.Vacation;
import cz.mendel.uni.services.TimePeriodService;
import cz.mendel.uni.services.VacationService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/vacation")
@SessionAttributes("vacation")
@AllArgsConstructor
public class VacationController {
    private VacationService vacationService;
    private TimePeriodService timePeriodService;

    @ApiOperation(value = "Get vacation by id")
    @GetMapping("/{id}")
    public String vacationInfo(@PathVariable("id") long id, Model model) {
        model.addAttribute("vacation", vacationService.findById(id));
        return "vacations/vacation-info";
    }

    @ApiOperation(value = "Create new vacation")
    @GetMapping("/add/new")
    public String addVacation(Model model) {
        model.addAttribute("vacation", Vacation.builder().timePeriod(new TimePeriod()).build());
        return "vacations/add-vacation";
    }

    @ApiOperation(value = "Create new vacation")
    @PostMapping("/add")
    public String add(Vacation vacation) {
        vacation.setTimePeriod(timePeriodService.save(vacation.getTimePeriod()));
        vacationService.save(vacation);
        return "redirect:/vacation/add/new";
    }

    @ApiOperation(value = "Edit vacation by id")
    @GetMapping("/edit/{id}")
    public String editVacation(@PathVariable("id") long id, Model model) {
        model.addAttribute("vacation", vacationService.findById(id));
        return "vacations/edit-vacation";
    }

    @ApiOperation(value = "Edit vacation")
    @PostMapping("/edit")
    public String edit(Vacation vacation) {
        vacationService.update(vacation);
        return "redirect:/vacation/add/new";
    }

    @ApiOperation(value = "Delete vacation by id")
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        vacationService.deleteById(id);
        return "redirect:/vacation/add/new";
    }
}
