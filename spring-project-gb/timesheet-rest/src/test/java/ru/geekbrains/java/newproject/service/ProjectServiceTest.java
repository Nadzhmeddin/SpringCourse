package ru.geekbrains.java.newproject.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import ru.geekbrains.java.newproject.model.Project;
import ru.geekbrains.java.newproject.repository.ProjectRepository;
import ru.geekbrains.java.newproject.repository.TimesheetRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static reactor.core.publisher.Mono.when;

@ActiveProfiles("test")
@SpringBootTest
class ProjectServiceTest {


    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    TimesheetRepository timesheetRepository;

    @Autowired
    ProjectService projectService;


    @Test
    void getByIdEmpty() {
        //given
        assertFalse(projectRepository.existsById(2L));

        assertTrue(projectService.getProjectById(2L).isEmpty());
    }

    @Test
    void findByIdPresent() {
        // given
        Project project = new Project();
        project.setId(150L);
        project.setName("projectName");
        project = projectRepository.save(project);

        // when
        Optional<Project> actual = projectService.getProjectById(project.getId());

        // then
        assertTrue(actual.isPresent());
        assertEquals(project.getId(), actual.get().getId());
        assertEquals(project.getName(), actual.get().getName());
    }
}