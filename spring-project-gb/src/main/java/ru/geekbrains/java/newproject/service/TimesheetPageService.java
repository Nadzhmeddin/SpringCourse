package ru.geekbrains.java.newproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.java.newproject.model.Employee;
import ru.geekbrains.java.newproject.model.Project;
import ru.geekbrains.java.newproject.model.Timesheet;
import ru.geekbrains.java.newproject.page.TimesheetPageDto;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class TimesheetPageService {

    private final TimesheetService timesheetService;
    private final ProjectService projectService;
    private final EmployeeService employeeService;

    public Optional<TimesheetPageDto> findById(Long id) {
        return timesheetService.getById(id)
                .map(this::convert);
    }

    private TimesheetPageDto convert(Timesheet timesheet) {
        Project project = projectService.getProjectById(timesheet.getProjectId())
                .orElseThrow();
        Employee employee = employeeService.getEmployeeById(timesheet.getEmployeeId())
                .orElseThrow();

        TimesheetPageDto dto = new TimesheetPageDto();
        dto.setProjectId(String.valueOf(project.getId()));
        dto.setEmployeeId(String.valueOf(employee.getId()));
        dto.setId(String.valueOf(timesheet.getId()));
        dto.setMinutes(String.valueOf(timesheet.getMinutes()));
        dto.setCreatedAt(timesheet.getCreatedAt().toString());

        return dto;

    }

    public List<TimesheetPageDto> findAll() {
        return timesheetService.getAll().stream()
                .map(this::convert)
                .toList();
    }

}
