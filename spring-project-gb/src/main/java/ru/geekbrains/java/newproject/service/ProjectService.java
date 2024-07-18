package ru.geekbrains.java.newproject.service;

import org.springframework.stereotype.Service;
import ru.geekbrains.java.newproject.model.Project;
import ru.geekbrains.java.newproject.model.Timesheet;
import ru.geekbrains.java.newproject.repository.ProjectRepository;
import ru.geekbrains.java.newproject.repository.TimesheetRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final TimesheetRepository timesheetRepository;

    public ProjectService(ProjectRepository projectRepository, TimesheetRepository timesheetRepository) {
        this.projectRepository = projectRepository;
        this.timesheetRepository = timesheetRepository;
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Optional<Project> getProjectById(Long id) {
        return projectRepository.findById(id);
    }


    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    public List<Timesheet> getAllTimesheetsByProjectId(Long id) {
        return timesheetRepository.findByProjectId(id);
    }
}
