package ru.geekbrains.java.newproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.java.newproject.model.Project;
import ru.geekbrains.java.newproject.page.ProjectPageDto;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ProjectPageService {

    private final ProjectService projectService;

    public Optional<ProjectPageDto> findById(Long id) {
        return projectService.getProjectById(id)
                .map(this::convert);
    }

    private ProjectPageDto convert(Project project) {

        ProjectPageDto projectPageDto = new ProjectPageDto();

        projectPageDto.setId(String.valueOf(project.getId()));
        projectPageDto.setName(project.getName());

        return projectPageDto;
    }

    public List<ProjectPageDto> findAll() {
        return projectService.getAllProjects().stream()
                .map(this::convert)
                .toList();
    }
}
