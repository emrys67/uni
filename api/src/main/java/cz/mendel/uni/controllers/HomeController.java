package cz.mendel.uni.controllers;

import org.springframework.web.bind.annotation.GetMapping;

public class HomeController {
    @GetMapping("/home")
    public String displayHomeProjects() {
        return "home/home";
    }
}
