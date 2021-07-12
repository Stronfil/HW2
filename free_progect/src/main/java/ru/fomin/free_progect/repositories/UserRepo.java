package ru.fomin.free_progect.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.fomin.free_progect.entities.UserEn;

import java.util.Optional;

public interface UserRepo extends JpaRepository<UserEn,Long> {

    Optional<UserEn> findByEmail(String email);

}
