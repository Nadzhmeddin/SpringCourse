package ru.geekbrains.java.newproject.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import ru.geekbrains.java.newproject.model.Employee;
import ru.geekbrains.java.newproject.model.Project;
import ru.geekbrains.java.newproject.model.Timesheet;
import ru.geekbrains.java.newproject.repository.EmployeeRepository;
import ru.geekbrains.java.newproject.repository.ProjectRepository;
import ru.geekbrains.java.newproject.repository.TimesheetRepository;


import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("tests")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class TimesheetControllerTest {

    @Autowired
    private TimesheetRepository timesheetRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    WebTestClient webTestClient;

    @BeforeEach
    void beforeEach() {
        timesheetRepository.deleteAll();
        projectRepository.deleteAll();
        employeeRepository.deleteAll();
    }



    @Test
    void getByIdResponseStatusNotFound() {
        webTestClient.get()
                .uri("/timesheets/-2")
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void getByIdTimesheet() {
        Project projectFirst = new Project();
        projectFirst.setName("Project#1");
        Project savedProject = projectRepository.save(projectFirst);

        Employee employee = new Employee();
        employee.setFirstName("John");
        Employee savedEmployee = employeeRepository.save(employee);

        Timesheet timesheet = new Timesheet();
        timesheet.setMinutes(200);
        timesheet.setProjectId(savedProject.getId());
        timesheet.setEmployeeId(savedEmployee.getId());
        Timesheet expected = timesheetRepository.save(timesheet);

        webTestClient.get()
                .uri("/timesheets/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Timesheet.class)
                .value(actual -> {
                    assertEquals(expected.getId(), actual.getId());
                    assertEquals(expected.getMinutes(), actual.getMinutes());
                    assertEquals(expected.getEmployeeId(), actual.getEmployeeId());
                    assertEquals(expected.getProjectId(), actual.getProjectId());
                });
    }

    @Test
    void getAllTimesheetsTest() {
        Timesheet firstTimesheet = new Timesheet();
        Timesheet secondTimesheet = new Timesheet();

        timesheetRepository.save(firstTimesheet);
        timesheetRepository.save(secondTimesheet);

        webTestClient.get()
                .uri("/timesheets")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(new ParameterizedTypeReference<Timesheet>() {
                })
                .contains(firstTimesheet, secondTimesheet);
    }


    @Test
    void postTimesheetTest() {
        Project projectFirst = new Project();
        projectFirst.setName("Project#1");
        Project savedProject = projectRepository.save(projectFirst);

        Employee employee = new Employee();
        employee.setFirstName("John");
        Employee savedEmployee = employeeRepository.save(employee);

        Timesheet timesheet = new Timesheet();
        timesheet.setId(1L);
        timesheet.setCreatedAt(LocalDate.now());
        timesheet.setProjectId(savedProject.getId());
        timesheet.setEmployeeId(savedEmployee.getId());
        timesheet.setMinutes(100);


        webTestClient.post()
                .uri("/timesheets")
                .bodyValue(timesheet)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Timesheet.class)
                .value(actual -> {
                    assertNotNull(actual.getId());
                    assertEquals(actual.getMinutes(), timesheet.getMinutes());
                });
    }

    @Test
    void deleteTimesheetTest() {
        Timesheet toDelete = new Timesheet();
        timesheetRepository.save(toDelete);

        webTestClient.delete()
                .uri("/timesheets/" +  toDelete.getId())
                .exchange()
                .expectStatus().isNoContent()
                .expectBody(Timesheet.class)
                .value(Assertions::assertNull);
    }

    @Test
    void putTimesheetTest() {
        Project projectFirst = new Project();
        projectFirst.setName("Project#1");
        Project savedProject = projectRepository.save(projectFirst);

        Employee employee = new Employee();
        employee.setFirstName("John");
        Employee savedEmployee = employeeRepository.save(employee);

        Timesheet timesheet = new Timesheet();
        timesheet.setCreatedAt(LocalDate.now());
        timesheet.setProjectId(savedProject.getId());
        timesheet.setEmployeeId(savedEmployee.getId());
        timesheet.setMinutes(100);

        timesheetRepository.save(timesheet);


        Project secondProject = new Project();
        secondProject.setName("Project#2");
        Project secondSavedProject = projectRepository.save(secondProject);


        Employee secondEmployee = new Employee();
        secondEmployee.setFirstName("Mike");
        Employee savedSecondEmployee = employeeRepository.save(secondEmployee);

        Timesheet secondTimesheet = new Timesheet();
        secondTimesheet.setId(2L);
        secondTimesheet.setCreatedAt(LocalDate.now().plusDays(10));
        secondTimesheet.setProjectId(secondSavedProject.getId());
        secondTimesheet.setEmployeeId(savedSecondEmployee.getId());
        secondTimesheet.setMinutes(350);



        webTestClient.put()
                .uri("/timesheets/" + timesheet.getId())
                .bodyValue(secondTimesheet)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Timesheet.class)
                .value(actual -> {
                    assertEquals(secondTimesheet.getId(), actual.getId());
                    assertEquals(secondTimesheet.getMinutes(), actual.getMinutes());
                    assertEquals(secondTimesheet.getCreatedAt(), actual.getCreatedAt());
                });
    }


}