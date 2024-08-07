package ru.geekbrains.java.newproject.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.geekbrains.java.newproject.model.Project;
import ru.geekbrains.java.newproject.repository.ProjectRepository;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("tests")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class ProjectControllerTest {

    @Autowired
    private ProjectRepository projectRepository;

//    @Autowired
//    WebTestClient webTestClient;

    @LocalServerPort
    private int port;
    private RestClient restClient;

    @BeforeEach
    void beforeEach() {
        restClient = RestClient.create("http://localhost:" + port);
    }


    @Test
    void getByIdNotFound() {

        assertThrows(HttpClientErrorException.NotFound.class, () ->{
            restClient.get()
                    .uri("/projects/-2")
                    .retrieve()
                    .toBodilessEntity();

        });
    }

    @Test
    void getByIdAllOk() {
        // save (project)
        // GET /projects/1L => 200 OK

        Project project = new Project();
        project.setName("projectName");
        Project expected = projectRepository.save(project);

        ResponseEntity<Project> actual = restClient.get()
                .uri("/projects/" + expected.getId())
                .retrieve()
                .toEntity(Project.class);

        // попробовать написать тесты с использованием WebTestClient

//        webTestClient.get()
//                .uri("/projects/" + expected.getId())
//                .exchange()
//                .expectStatus().isOk() // assertEquals(HttpStatus.OK, actual.getStatusCode());
//                .expectBody(Project.class)
//                        .value(actual -> {
//                            assertEquals(expected.getId(), actual.getId());
//                            assertEquals(expected.getName(), actual.getName());
//                        });

        // assert 200 OK
        assertEquals(HttpStatus.OK, actual.getStatusCode());
        Project responseBody = actual.getBody();
        assertEquals(expected.getId(), responseBody.getId());
        assertEquals(expected.getName(), responseBody.getName());

    }

    @Test
    void testCreate() {
        Project toCreate = new Project();
        toCreate.setName("NewName");


        ResponseEntity<Project> response = restClient.post()
                .uri("/projects")
                .body(toCreate)
                .retrieve()
                .toEntity(Project.class);

        // Проверяем HTTP-ручку сервера
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Project responseBody = response.getBody();
        assertNotNull(responseBody);
        assertNotNull(responseBody.getId());
        assertEquals(responseBody.getName(), toCreate.getName());

        // Проверяем, что запись в БД есть!
        assertTrue(projectRepository.existsById(responseBody.getId()));
    }

    @Test
    void testDeleteById() {

        Project toDelete = new Project();
        toDelete.setName("newName");
        toDelete = projectRepository.save(toDelete);

        ResponseEntity<Void> response = restClient.delete()
                .uri("/projects/" + toDelete.getId())
                .retrieve()
                .toBodilessEntity(); // если нет тела ответа

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        assertFalse(projectRepository.existsById(toDelete.getId()));
    }
}