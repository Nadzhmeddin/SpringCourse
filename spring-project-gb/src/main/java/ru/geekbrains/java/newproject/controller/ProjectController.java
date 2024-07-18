package ru.geekbrains.java.newproject.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.java.newproject.model.Project;
import ru.geekbrains.java.newproject.model.Timesheet;
import ru.geekbrains.java.newproject.service.ProjectService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService service;

    public ProjectController(ProjectService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllProjects());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Project>> getProjectById(@PathVariable Long id) {
        Optional<Project> project = service.getProjectById(id);
        if(project.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(project);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        project = service.createProject(project);
        return ResponseEntity.status(HttpStatus.CREATED).body(project);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        service.deleteProject(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}/timesheets")
    public ResponseEntity<List<Timesheet>> getAllTimesheetsByProjectId(@PathVariable Long id) {
        List<Timesheet> timesheetsByOneProject = service.getAllTimesheetsByProjectId(id);

        return ResponseEntity.status(HttpStatus.OK).body(timesheetsByOneProject);
    }
}
