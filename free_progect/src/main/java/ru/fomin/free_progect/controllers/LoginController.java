package ru.fomin.free_progect.controllers;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginController {

    @GetMapping
    public String getLoginPage() {
        return "login";
    }

}
