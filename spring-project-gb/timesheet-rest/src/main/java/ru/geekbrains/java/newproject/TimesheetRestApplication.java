package ru.geekbrains.java.newproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import ru.geekbrains.java.newproject.model.*;
import ru.geekbrains.java.newproject.repository.*;



import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


@EnableDiscoveryClient
@SpringBootApplication
public class TimesheetRestApplication {
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(TimesheetRestApplication.class, args);

		UserRepository userRepository = context.getBean(UserRepository.class);
		UserRoleRepository roleRepository = context.getBean(UserRoleRepository.class);

		UserRole adminAdminRole = new UserRole();
		adminAdminRole.setName(RoleName.ADMIN.getName());

		UserRole userRole = new UserRole();
		userRole.setName(RoleName.USER.getName());

		UserRole restRole = new UserRole();
		restRole.setName(RoleName.REST.getName());

		roleRepository.save(adminAdminRole);
		roleRepository.save(userRole);
		roleRepository.save(restRole);

		User admin = new User();
		admin.setLogin("admin");
		admin.setPassword("$2a$12$K/7CBKfndmZWvKY5yv0Hl.WYJ6L/sdedTruTv/eEwtRDp1Z14jVhq");
		admin.setUserRoleList(List.of(adminAdminRole));


		User user = new User();
		user.setLogin("user");
		user.setPassword("$2a$12$2tuRseAXEKjXqowpEVfK8uptvTkaosA8isxIeXFYjZm4AH/SOW8Zu");
		user.setUserRoleList(List.of(userRole));



		User restUser = new User();
		restUser.setLogin("rest");
		restUser.setPassword("$2a$12$a7wuOzS08HIh8ozIXGkkQOUx6JOtU30530WIh5WtV8slV5L7BETuq");
		restUser.setUserRoleList(List.of(restRole));

		userRepository.save(admin);
		userRepository.save(user);
		userRepository.save(restUser);





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
