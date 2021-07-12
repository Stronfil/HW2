package ru.fomin.sql2o.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/")
    public String redirectToBookList(){
        return "redirect:/book";
    }

}
