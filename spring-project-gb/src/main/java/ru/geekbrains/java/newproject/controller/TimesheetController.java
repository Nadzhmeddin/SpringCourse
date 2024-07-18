package ru.geekbrains.java.newproject.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/{id}") // получить одной записи
    public ResponseEntity<Timesheet> get(@PathVariable Long id) {
        Optional<Timesheet> timesheet = service.getById(id);

        if(timesheet.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(timesheet.get());
//            return ResponseEntity.ok().body(ts.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping // получить все записи
    public ResponseEntity<List<Timesheet>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @PostMapping // создание нового ресурса
    public ResponseEntity<Timesheet> createTimesheet(@RequestBody Timesheet timesheet) {
        timesheet = service.createTimesheet(timesheet);
        if(timesheet.getId() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(timesheet);
    }

    // /timesheets/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTimesheet(@PathVariable Long id) {
        service.deleteTimesheet(id);

        // 204 no content
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/createdAtAfter")
    public ResponseEntity<List<Timesheet>> getTimesheetCreatedAtAfter(@RequestParam(name = "createdAtAfter") LocalDate createdAtAfter) {
        List<Timesheet> timesheets = service.createdAtAfter(createdAtAfter);

        return ResponseEntity.status(HttpStatus.OK).body(timesheets);
    }

    @GetMapping("/createdAtBefore")
    public ResponseEntity<List<Timesheet>> getTimesheetCreatedAtBefore(@RequestParam(name = "createdAtBefore") LocalDate createdAtBefore) {
        List<Timesheet> timesheets = service.createdAtAfter(createdAtBefore);

        return ResponseEntity.status(HttpStatus.OK).body(timesheets);
    }
}
