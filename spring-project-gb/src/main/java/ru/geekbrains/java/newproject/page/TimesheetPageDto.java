package ru.geekbrains.java.newproject.page;

import lombok.Data;

@Data
public class TimesheetPageDto {

    private String projectId;
    private String employeeId;
    private String id;
    private String minutes;
    private String createdAt;
}
