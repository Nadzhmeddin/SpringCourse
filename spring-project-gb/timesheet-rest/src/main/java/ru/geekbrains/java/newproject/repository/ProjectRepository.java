package ru.geekbrains.java.newproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.java.newproject.model.Project;


@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
