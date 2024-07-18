package ru.geekbrains.java.newproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.java.newproject.model.Employee;
import ru.geekbrains.java.newproject.model.Timesheet;
import ru.geekbrains.java.newproject.service.EmployeeService;
import ru.geekbrains.java.newproject.service.TimesheetService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService service;
    private final TimesheetService timesheetService;


    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok().body(service.getAllEmployees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Employee>> getEmployeeById(@PathVariable Long id) {
        Optional<Employee> employee = service.getEmployeeById(id);
        if(employee.isPresent()) {
            return ResponseEntity.ok().body(employee);
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createEmployee(employee));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        service.deleteEmployeeById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/timesheets")
    public ResponseEntity<List<Timesheet>> findAllProjectByEmployeeId(@PathVariable Long id) {
        return ResponseEntity.ok().body(timesheetService.findAllTimesheetsByEmployeeId(id));

    }
}
