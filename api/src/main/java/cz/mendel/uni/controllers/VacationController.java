package cz.mendel.uni.controllers;

import cz.mendel.uni.entities.TimePeriod;
import cz.mendel.uni.entities.Vacation;
import cz.mendel.uni.services.TimePeriodService;
import cz.mendel.uni.services.VacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

public class VacationController {
    @Autowired
    private VacationService vacationService;
    @Autowired
    private TimePeriodService timePeriodService;

    @GetMapping("/{id}")
    public String vacationInfo(@PathVariable("id") long id, Model model) {
        Vacation vacation = vacationService.findById(id);
        model.addAttribute("vacation", vacation);
        return "vacations/vacation-info";
    }

    @GetMapping("/add/new")
    public String addVacation(Model model){
        Vacation vacation = new Vacation();
        vacation.setTimePeriod(new TimePeriod());
        model.addAttribute("vacation", vacation);
        return "vacations/add-vacation";
    }
    @PostMapping("/add")
    public String add(@ModelAttribute Vacation vacation){
        vacationService.save(vacation);
        return "redirect:/vacation/add/new";
    }

    @GetMapping("/edit/{id}")
    public String editVacatopn(@PathVariable("id")long id, Model model){
        Vacation vacation = vacationService.findById(id);
        model.addAttribute("vacation", vacation);
        return "vacations/edit-vacation";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id")long id){
        vacationService.deleteById(id);
        return "redirect:/vacation/add/new";
    }
}
