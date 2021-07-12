package ru.fomin.free_progect.controllers;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MainController {

    @RequestMapping
    public String getMainPage(){
        return "index";
    }

}
