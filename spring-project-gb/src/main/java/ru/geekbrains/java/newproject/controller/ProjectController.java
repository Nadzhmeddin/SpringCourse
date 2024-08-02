package ru.geekbrains.java.newproject.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.java.newproject.model.Project;
import ru.geekbrains.java.newproject.model.Timesheet;
import ru.geekbrains.java.newproject.service.ProjectService;
import ru.geekbrains.java.newproject.API;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projects")
@Tag(name = "Projects", description = "API Для работы с проектами")
public class ProjectController {

    private final ProjectService service;

    public ProjectController(ProjectService service) {
        this.service = service;
    }


    @Operation(summary = "Get All Projects", description = "Получение всех проектов в БД")
    @API.NotFoundResponse
    @API.ErrorFromServerResponse
    @API.OKResponse
    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllProjects());
    }

    @Operation(summary = "Get Project", description = "Получить проект по его идентификатору")
    @API.NotFoundResponse
    @API.ErrorFromServerResponse
    @API.OKResponse
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Project>> getProjectById(@PathVariable @Parameter(description = "Идентификатор проекта") Long id) {
        Optional<Project> project = service.getProjectById(id);
        if(project.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(project);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Operation(summary = "Create Project", description = "Создание проекта")
    @API.NotFoundResponse
    @API.ErrorFromServerResponse
    @API.OKResponse
    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody @Parameter(description = "Передается объект проект в виде JSON со всеми полями") Project project) {
        project = service.createProject(project);
        return ResponseEntity.status(HttpStatus.CREATED).body(project);
    }

    @Operation(summary = "Delete Project", description = "Удаление проекта по заданному идентификатору")
    @API.NotFoundResponse
    @API.ErrorFromServerResponse
    @API.OKResponse
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable @Parameter(description = "Идентификатор проекта") Long id) {
        service.deleteProject(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Get all Timesheets by Project id", description = "Получение ")
    @API.NotFoundResponse
    @API.ErrorFromServerResponse
    @API.OKResponse
    @GetMapping("/{id}/timesheets")
    public ResponseEntity<List<Timesheet>> getAllTimesheetsByProjectId(@PathVariable @Parameter(description = "Идентификатор проекта") Long id) {
        List<Timesheet> timesheetsByOneProject = service.getAllTimesheetsByProjectId(id);

        return ResponseEntity.status(HttpStatus.OK).body(timesheetsByOneProject);
    }
}
