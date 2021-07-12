package ru.fomin.free_progect.services;

import ru.fomin.free_progect.entities.UserEn;

import java.util.Optional;

public interface UserService {

    Optional<UserEn> findByUsername(String email);

    UserEn findCurrentUser();

}
