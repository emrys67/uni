package cz.mendel.uni.controllers;

import cz.mendel.uni.entities.Classroom;
import cz.mendel.uni.services.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClassroomController {
//    @Autowired
//    ClassroomService classroomService;
//    @GetMapping("/")
//    public String get(){
//        System.out.println(classroomService.save(new Classroom(1, 50)));
//        System.out.println(classroomService.getById(1).toString());
//        return "index";
//    }
}
