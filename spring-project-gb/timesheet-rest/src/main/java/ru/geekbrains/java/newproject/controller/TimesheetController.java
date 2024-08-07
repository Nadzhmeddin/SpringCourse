package ru.geekbrains.java.newproject.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.java.newproject.API;
import ru.geekbrains.java.newproject.model.Timesheet;
import ru.geekbrains.java.newproject.service.TimesheetService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/timesheets")
public class TimesheetController {

    // GET - получение - не содержит body
    // POST - создание
    // PUT - изменение
    // DELETE -удаление

    // @GetMapping("/timesheets/{id}") // получить конкретную запись по идентификатору
    // @DeleteMapping("timesheets/{id}") // удалить конкретную запись по идентификатору
    // @PutMapping("timesheets/{id}") // изменить конкретную запись по идентификатору

    private final TimesheetService service;

    public TimesheetController(TimesheetService service) {
        this.service = service;
    }

    @Operation(summary = "Get Timesheet by Id", description = "Получение табеля по заданному идентификатору")
    @API.NotFoundResponse
    @API.ErrorFromServerResponse
    @API.OKResponse
    @GetMapping("/{id}") // получить одной записи
    public ResponseEntity<Timesheet> get(@PathVariable @Parameter(description = "Идентификатор табеля") Long id) {
        Optional<Timesheet> timesheet = service.getById(id);

        if(timesheet.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(timesheet.get());
//            return ResponseEntity.ok().body(ts.get());
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Get all timesheets", description = "Получение списка всех табелей")
    @API.NotFoundResponse
    @API.ErrorFromServerResponse
    @API.OKResponse
    @GetMapping // получить все записи
    public ResponseEntity<List<Timesheet>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @Operation(summary = "Create timesheet", description = "Создание табеля")
    @API.NotFoundResponse
    @API.ErrorFromServerResponse
    @API.OKResponse
    @PostMapping // создание нового ресурса
    public ResponseEntity<Timesheet> createTimesheet(@RequestBody @Parameter(description = "Передается объект табель со всеми полями в виде JSON") Timesheet timesheet) {
        timesheet = service.createTimesheet(timesheet);
        if(timesheet.getId() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(timesheet);
    }

    @Operation(summary = "Delete timesheet", description = "Удаление табеля по заданному идентификатору")
    @API.NotFoundResponse
    @API.ErrorFromServerResponse
    @API.OKResponse
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTimesheet(@PathVariable @Parameter(description = "Идентификатор табеля") Long id) {
        service.deleteTimesheet(id);

        // 204 no content
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Timesheet> updateTimesheet(@RequestBody Timesheet timesheet, @PathVariable Long id) {
        return ResponseEntity.ok().body(timesheet);
    }



    @Operation(summary = "Get list timesheets", description = "Получение списка табелей созданных после заданной даты")
    @API.NotFoundResponse
    @API.ErrorFromServerResponse
    @API.OKResponse
    @GetMapping("/createdAtAfter")
    public ResponseEntity<List<Timesheet>> getTimesheetCreatedAtAfter(@RequestParam(name = "createdAtAfter") @Parameter(description = "Дата создания после") LocalDate createdAtAfter) {
        List<Timesheet> timesheets = service.createdAtAfter(createdAtAfter);

        return ResponseEntity.status(HttpStatus.OK).body(timesheets);
    }

    @Operation(summary = "Get list timesheets", description = "Получение списка табелей созданных до заданной даты")
    @API.NotFoundResponse
    @API.ErrorFromServerResponse
    @API.OKResponse
    @GetMapping("/createdAtBefore")
    public ResponseEntity<List<Timesheet>> getTimesheetCreatedAtBefore(@RequestParam(name = "createdAtBefore") @Parameter(description = "Дата создания до") LocalDate createdAtBefore) {
        List<Timesheet> timesheets = service.createdAtAfter(createdAtBefore);

        return ResponseEntity.status(HttpStatus.OK).body(timesheets);
    }
}
