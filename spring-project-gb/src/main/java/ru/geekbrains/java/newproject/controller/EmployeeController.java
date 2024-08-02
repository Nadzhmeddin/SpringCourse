package ru.geekbrains.java.newproject.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.java.newproject.API;
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


    @Operation(summary = "Get All Employees", description = "Получение всех сотрудников")
    @API.NotFoundResponse
    @API.ErrorFromServerResponse
    @API.OKResponse
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok().body(service.getAllEmployees());
    }

    @Operation(summary = "Get Employee", description = "Получение сотрудника по заданному идентификатору")
    @API.NotFoundResponse
    @API.ErrorFromServerResponse
    @API.OKResponse
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Employee>> getEmployeeById(@PathVariable @Parameter(description = "Идентификатор сотрудника") Long id) {
        Optional<Employee> employee = service.getEmployeeById(id);
        if(employee.isPresent()) {
            return ResponseEntity.ok().body(employee);
        }
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Create Employee", description = "Создание сотрудника")
    @API.NotFoundResponse
    @API.ErrorFromServerResponse
    @API.OKResponse
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody @Parameter(description = "Передается объект Сотрудник со всеми параметрами") Employee employee) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createEmployee(employee));
    }

    @Operation(summary = "Delete Employee", description = "Удаление сотрудника по заданному идентификатору")
    @API.NotFoundResponse
    @API.ErrorFromServerResponse
    @API.OKResponse
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable @Parameter(description = "Идентификатор проекта") Long id) {
        service.deleteEmployeeById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Find projects by ID employee", description = "Поиск проектов по заданному идентификатору сотрудника")
    @API.NotFoundResponse
    @API.ErrorFromServerResponse
    @API.OKResponse
    @GetMapping("/{id}/timesheets")
    public ResponseEntity<List<Timesheet>> findAllProjectByEmployeeId(@PathVariable @Parameter(description = "Идентификатор проекта") Long id) {
        return ResponseEntity.ok().body(timesheetService.findAllTimesheetsByEmployeeId(id));

    }
}
