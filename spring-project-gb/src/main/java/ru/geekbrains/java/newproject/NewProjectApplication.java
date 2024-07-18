package ru.geekbrains.java.newproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.geekbrains.java.newproject.model.Employee;
import ru.geekbrains.java.newproject.model.Project;
import ru.geekbrains.java.newproject.model.Timesheet;
import ru.geekbrains.java.newproject.repository.EmployeeRepository;
import ru.geekbrains.java.newproject.repository.ProjectRepository;
import ru.geekbrains.java.newproject.repository.TimesheetRepository;


import java.time.LocalDate;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class NewProjectApplication {

	/**
	 * Spring-data -
	 * spring-data-jdbc - зависимость, которая предоставляет удобные преднастроенные инструменты
	 * для работы с реляционными БД
	 * spring-data-jpa - библиотека, которая предоставляет удобные преднастроенные инструменты
	 * для работы с JPA
	 *  spring-data-jpa
	 *   /
	 *  /
	 * jpa <------ hibernate (реализация JPA)
	 *
	 * spring-data-mongo - зависимость, которая предоставляет инструменты для работы с mongo
	 *
	 */

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(NewProjectApplication.class, args);

		ProjectRepository projectRepository = context.getBean(ProjectRepository.class);
		for (int i = 0; i <= 5; i++) {
			Project project = new Project();
			project.setName("Project #" + i);
			projectRepository.save(project);
		}

		EmployeeRepository employeeRepository = context.getBean(EmployeeRepository.class);

		for (int i = 0; i < 10; i++) {
			Employee employee = new Employee();
			employee.setFirstName("Employee Mr." + i);
			employee.setAge(20 + i);
			employee.setLastName("LastName #"+i);
			employeeRepository.save(employee);
		}

		TimesheetRepository repository = context.getBean(TimesheetRepository.class);
		LocalDate createdAt = LocalDate.now();
		for (int i = 0; i <= 10; i++) {
			createdAt = createdAt.plusDays(1);

			Timesheet timesheet = new Timesheet();
			timesheet.setEmployeeId(ThreadLocalRandom.current().nextLong(1, 11));
			timesheet.setProjectId(ThreadLocalRandom.current().nextLong(1, 6));
			timesheet.setCreatedAt(createdAt);
			timesheet.setMinutes(ThreadLocalRandom.current().nextInt(100,1000));

			repository.save(timesheet);
		}

	}

}
