package ru.geekbrains.java.newproject.service;

import org.springframework.stereotype.Service;
import ru.geekbrains.java.newproject.model.Project;
import ru.geekbrains.java.newproject.model.Timesheet;
import ru.geekbrains.java.newproject.repository.ProjectRepository;
import ru.geekbrains.java.newproject.repository.TimesheetRepository;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service // то же самое, что и Component
public class TimesheetService {

    private final TimesheetRepository timesheetRepository;
    private final ProjectRepository projectRepository;

    public TimesheetService(TimesheetRepository timesheetRepository, ProjectRepository projectRepository) {
        this.timesheetRepository = timesheetRepository;
        this.projectRepository = projectRepository;
    }

    // получить одной записи
    public Optional<Timesheet> getById(Long id) {
        return timesheetRepository.findById(id);
    }

    // получить все записи
    public List<Timesheet> getAll() {
        return timesheetRepository.findAll();
    }

    // создание нового ресурса
    public Timesheet createTimesheet(Timesheet timesheet) {
        Long newProjectId = timesheet.getProjectId();
        List<Project> allProjects = projectRepository.findAll();
        for (Project project : allProjects) {
            if(project.getId().equals(newProjectId)) {
//                timesheet.setCreatedAt(LocalDate.now());
                timesheet = timesheetRepository.save(timesheet);
            }
        }
        return timesheet;
    }

    // удаление записи
    public void deleteTimesheet(Long id) {
        timesheetRepository.deleteById(id);
    }

    public List<Timesheet> createdAtAfter(LocalDate date) {
        return timesheetRepository.findAll().stream()
                .filter(t -> t.getCreatedAt().isAfter(date))
                .collect(Collectors.toList());
    }

    public List<Timesheet> createdAtBefore(LocalDate date) {
        return timesheetRepository.findAll().stream()
                .filter(t -> t.getCreatedAt().isBefore(date))
                .collect(Collectors.toList());
    }

    public List<Timesheet> findAllTimesheetsByEmployeeId(Long id) {
        return timesheetRepository.findByEmployeeId(id);
    }
}
