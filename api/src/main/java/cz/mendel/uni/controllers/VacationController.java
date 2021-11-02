package cz.mendel.uni.controllers;

import cz.mendel.uni.entities.TimePeriod;
import cz.mendel.uni.entities.Vacation;
import cz.mendel.uni.services.TimePeriodService;
import cz.mendel.uni.services.VacationService;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/vacation")
@SessionAttributes("vacation")
@AllArgsConstructor
public class VacationController {
    private VacationService vacationService;
    private TimePeriodService timePeriodService;

    @GetMapping("/{id}")
    public String vacationInfo(@PathVariable("id") long id, Model model) {
        Vacation vacation = vacationService.findById(id);
        model.addAttribute("vacation", vacation);
        return "vacations/vacation-info";
    }

    @GetMapping("/add/new")
    public String addVacation(Model model) {
        Vacation vacation = new Vacation();
        vacation.setTimePeriod(new TimePeriod());
        model.addAttribute("vacation", vacation);
        return "vacations/add-vacation";
    }

    @PostMapping("/add")
    public String add(Vacation vacation) {
        TimePeriod timePeriod = timePeriodService.save(vacation.getTimePeriod());
        vacation.setTimePeriod(timePeriod);
        vacationService.save(vacation);
        return "redirect:/vacation/add/new";
    }

    @GetMapping("/edit/{id}")
    public String editVacatopn(@PathVariable("id") long id, Model model) {
        Vacation vacation = vacationService.findById(id);
        model.addAttribute("vacation", vacation);
        return "vacations/edit-vacation";
    }

    @PostMapping("/edit")
    public String edit(Vacation vacation) {
        vacationService.update(vacation);
        return "redirect:/vacation/add/new";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        vacationService.deleteById(id);
        return "redirect:/vacation/add/new";
    }
}
