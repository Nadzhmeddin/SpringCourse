package ru.geekbrains.java.newproject;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import ru.geekbrains.java.newproject.model.Project;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class API {

    @Retention(RetentionPolicy.RUNTIME)
    @ApiResponse(description = "Проект не найден", responseCode = "404", content = @Content(schema = @Schema(implementation = Void.class)))
    public @interface NotFoundResponse{
    }

    @Retention(RetentionPolicy.RUNTIME)
    @ApiResponse(description = "Внутренняя ошибка", responseCode = "500", content = @Content(schema = @Schema(implementation = Void.class)))
    public @interface ErrorFromServerResponse{

    }

    @Retention(RetentionPolicy.RUNTIME)
    @ApiResponse(description = "Успешный ответ", responseCode = "200", content = @Content(schema = @Schema(implementation = Project.class)))
    public @interface OKResponse {

    }
}
