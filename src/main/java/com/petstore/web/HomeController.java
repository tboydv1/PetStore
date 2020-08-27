package com.petstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String welcomePage(Model model){

        model.addAttribute("message", "Welcome to Pet Lovers Home page");
        return "index";
    }
}
