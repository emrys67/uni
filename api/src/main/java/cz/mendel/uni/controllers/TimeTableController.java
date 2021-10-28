//package cz.mendel.uni.controllers;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import java.util.List;
//
//public class TimeTableController {
//    @Autowired
//    private LectureService lectureService;
//
//    @GetMapping("/list")
//    public String list(Model model) {
//        List<Lecture> lectures = lectureService.getAllLectures();
//        model.addAttribute("lectures", lectures);
//        return "timetable/get-timetable";
//    }
//}
