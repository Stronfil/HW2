package ru.fomin.sec.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.fomin.sec.service.UserService;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getScore(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        Integer score = userService.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new)
                .getScore();
        return ResponseEntity.ok(score);
    }

}
