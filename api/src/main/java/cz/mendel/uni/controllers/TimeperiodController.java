package cz.mendel.uni.controllers;

import cz.mendel.uni.entities.TimePeriod;
import cz.mendel.uni.services.TimePeriodService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static java.lang.String.format;
@SessionAttributes("timeperiod")
@RequestMapping("/timeperiod")
@AllArgsConstructor
public class TimeperiodController {
    private TimePeriodService timePeriodService;

    @ApiOperation(value = "Get timeperiod by id")
    @GetMapping("/{id}")
    public String timeperiodInfo(@PathVariable("id") long id, Model model) {
        TimePeriod timeperiod = timePeriodService.findById(id);
        model.addAttribute("timeperiod", timeperiod);
        return "timeperiods/timeperiod-info";
    }

    @ApiOperation(value = "Create new timeperiod")
    @GetMapping("/add/new")
    public String addTimeperiod(Model model) {
        TimePeriod timePeriod = new TimePeriod();
        model.addAttribute("timeperiod", timePeriod);
        return "timeperiods/add-timeperiod";
    }

    @ApiOperation(value = "Create new timeperiod")
    @PostMapping("/add")
    public String createTimeperiod(TimePeriod timePeriod) {
        timePeriodService.save(timePeriod);
        return "redirect:/timeperiod/add/new";
    }

    @ApiOperation(value = "Edit timeperiod by id")
    @GetMapping("/edit/{id}")
    public String editTimeperiod(@PathVariable("id") long id, Model model) {
        TimePeriod timePeriod = timePeriodService.findById(id);
        model.addAttribute("timeperiod", timePeriod);
        System.out.println(timePeriod.getId());
        return "timeperiods/edit-timeperiod";
    }

    @ApiOperation(value = "Edit timeperiod")
    @PostMapping("/edit")
    public String editTimeperiod(TimePeriod timePeriod) {
        System.out.println(timePeriod.getId());
        timePeriodService.update(timePeriod);
        return format("redirect:/timeperiod/%s", timePeriod.getId());
    }

    @ApiOperation(value = "Delete timeperiod by id")
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        timePeriodService.deleteById(id);
        return "redirect:/classroom//add/new";
    }
}
