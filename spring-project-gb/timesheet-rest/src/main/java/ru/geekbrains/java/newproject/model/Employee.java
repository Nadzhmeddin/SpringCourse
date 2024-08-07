package ru.geekbrains.java.newproject.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Table(name = "employee_tbl")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "employee_id")
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;

    @ManyToMany
    @JoinTable(
            name = "project_employee_tbl",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id"))
    private List<Project> projectList;
}
