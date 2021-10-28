//package cz.mendel.uni.controllers;
//
//import cz.mendel.uni.entities.Subject;
//import cz.mendel.uni.entities.Teacher;
//import cz.mendel.uni.services.SubjectService;
//import cz.mendel.uni.services.TeacherService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import java.util.List;
//
//public class SubjectController {
//    @Autowired
//    SubjectService subjectService;
//    @Autowired
//    TeacherService teacherService;
//
//    @GetMapping("/list")
//    public String list(Model model) {
//        List<Subject> subjects = subjectService.findAll();
//        model.addAttribute("subjects", subjects);
//        return "subjects/get-subjects";
//    }
//
//    @GetMapping("/{id}")
//    public String subjectInfo(@PathVariable("id") long id, Model model) {
//        Subject subject = subjectService.findById(id);
//        model.addAttribute("subject", subject);
//        return "subjects/subject-info";
//    }
//
//    @GetMapping("/add/new")
//    public String createSubject(Model model){
//        Subject subject = new Subject();
//        Teacher teacher = new Teacher();
//        List<Teacher> teachers = teacherService.findAll();
//        model.addAttribute("teachers", teachers);
//        model.addAttribute("subject", subject);
//        return "subjects/add-subject";
//    }
//
//    @PostMapping("/add")
//    public String addSubject(@ModelAttribute Subject subject){
//        subjectService.save(subject);
//        subjectService.save(subject.getSupervisor(), subject);
//        return "redirect:/subjects/add/new";
//    }
//
//    @GetMapping("/edit/{id}")
//    public String editSubject(@PathVariable("id") long id, Model model){
//        Subject subject = subjectService.findById(id);
//        model.addAttribute("subject", subject);
//        return "subjects/edit-subject";
//    }
//
//    @GetMapping("/delete/{id}")
//    public String delete(@PathVariable("id") long id){
//        subjectService.deleteById(id);
//        return "redirect:/subjects/list";
//    }
//}
