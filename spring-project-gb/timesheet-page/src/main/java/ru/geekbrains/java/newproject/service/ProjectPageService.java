package ru.geekbrains.java.newproject.service;


import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import ru.geekbrains.java.newproject.client.ProjectResponse;
import ru.geekbrains.java.newproject.page.ProjectPageDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;


@Service
public class ProjectPageService {

    private final DiscoveryClient discoveryClient;

    public ProjectPageService(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }


    private RestClient restClient() {

        List<ServiceInstance> instances = discoveryClient.getInstances("TIMESHEET-REST");
        int instancesCount = instances.size();
        int instancesIndex = ThreadLocalRandom.current().nextInt(0, instancesCount);
        ServiceInstance instance = instances.get(instancesIndex);
        String uri = "http://" + instance.getHost() + ":" + instance.getPort();
        return RestClient.create(uri);
    }

    public List<ProjectPageDto> findAll() {
        List<ProjectResponse> projects = restClient().get()
                .uri("/projects")
                .retrieve()
                .body(new ParameterizedTypeReference<List<ProjectResponse>>() {
                });

        List<ProjectPageDto> result = new ArrayList<>();
        for (ProjectResponse project : projects) {
            ProjectPageDto projectPageDto = new ProjectPageDto();
            projectPageDto.setId(String.valueOf(project.getId()));
            projectPageDto.setName(project.getName());

            result.add(projectPageDto);
        }
        return result;
    }

    public Optional<ProjectPageDto> findById(Long id) {

        try {
            ProjectResponse project = restClient().get()
                    .uri("/projects/" + id)
                    .retrieve()
                    .body(ProjectResponse.class);

            ProjectPageDto projectPageDto = new ProjectPageDto();
            projectPageDto.setId(String.valueOf(project.getId()));
            projectPageDto.setName(project.getName());

            return Optional.of(projectPageDto);
        } catch (HttpClientErrorException.NotFound e) {
            return Optional.empty();
        }
    }

}
