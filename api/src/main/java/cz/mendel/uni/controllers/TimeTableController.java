package cz.mendel.uni.controllers;

import cz.mendel.uni.entities.Lecture;
import cz.mendel.uni.services.LectureService;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/timetable")
public class TimeTableController {
    private LectureService lectureService;

    @GetMapping("/list")
    public String list(Model model) {
        List<Lecture> lectures = lectureService.findAll();
//        lectures = lectureService.sortLectures(lectures);
        model.addAttribute("lectures", lectures);
        return "timetable/get-timetable";
    }
}
