package ru.geekbrains.java.newproject.client;


import lombok.*;

@Data
public class EmployeeResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
}
