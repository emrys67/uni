package cz.mendel.uni.controllers;

import cz.mendel.uni.entities.TimePeriod;
import cz.mendel.uni.services.TimePeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import static java.lang.String.format;

public class TimeperiodController {
    @Autowired
    private TimePeriodService timePeriodService;

    @GetMapping("/{id}")
    public String timeperiodInfo(@PathVariable("id") long id, Model model) {
        TimePeriod timeperiod = timePeriodService.findById(id);
        model.addAttribute("timeperiod", timeperiod);
        return "timeperiods/timeperiod-info";
    }

    @GetMapping("/add/new")
    public String addTimeperiod(Model model){
        TimePeriod timePeriod = new TimePeriod();
        model.addAttribute("timeperiod", timePeriod);
        return "timeperiods/add-timeperiod";
    }

    @PostMapping("/add")
    public String createTimeperiod(@ModelAttribute TimePeriod timePeriod){
        timePeriodService.save(timePeriod);
        return "redirect:/timeperiod/add";
    }

    @GetMapping("/edit/{id}")
    public String editTimeperiod(@PathVariable("id")long id, Model model){
        TimePeriod timePeriod = timePeriodService.findById(id);
        model.addAttribute("timeperiod", timePeriod);
        return "timeperiods/edit-timeperiod";
    }

    @PostMapping("/edit")
    public String editTimeperiod(@ModelAttribute TimePeriod timePeriod){
        timePeriodService.save(timePeriod);
        return format("redirect:/timeperiod/edit/%s", timePeriod.getId());
    }
}
