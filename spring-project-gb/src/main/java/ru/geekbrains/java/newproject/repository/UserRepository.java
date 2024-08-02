package ru.geekbrains.java.newproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.java.newproject.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLogin(String login);

}
