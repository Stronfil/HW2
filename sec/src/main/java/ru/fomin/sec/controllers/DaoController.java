package ru.fomin.sec.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.fomin.sec.entities.User;
import ru.fomin.sec.service.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/get")
@RequiredArgsConstructor
public class DaoController {

    private final UserService userService;

    @GetMapping("/current")
    public String getCurrent(Principal principal) {
        SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(principal.getName()).get();
        return "authenticated: " + user.getName() + "  score: " + user.getScore();
    }

    @GetMapping("/set")
    public String setScore(@RequestParam Integer score, Principal principal){
        userService.setScore(score, principal.getName());
        User user = userService.findByUsername(principal.getName()).get();
        return "authenticated: " + user.getName() + "  score: " + user.getScore();
    }

}
