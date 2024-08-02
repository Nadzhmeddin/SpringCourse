package ru.geekbrains.java.newproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.java.newproject.model.UserRole;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    List<UserRole> findUserRoleById(Long id);
}
