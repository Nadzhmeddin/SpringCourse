package ru.geekbrains.java.newproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.java.newproject.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
