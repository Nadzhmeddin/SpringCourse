package ru.geekbrains.java.newproject.service;

import org.springframework.stereotype.Service;
import ru.geekbrains.java.newproject.model.Employee;
import ru.geekbrains.java.newproject.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private Long count = 1L;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }

    public Employee createEmployee(Employee employee) {
        employee.setId(count++);
        return employeeRepository.save(employee);
    }
}
