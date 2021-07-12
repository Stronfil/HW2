package ru.fomin.sec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.fomin.sec.entities.User;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {

    Optional<User> findByName(String name);

}
