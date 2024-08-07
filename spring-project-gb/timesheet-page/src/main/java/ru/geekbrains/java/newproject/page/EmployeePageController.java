package ru.geekbrains.java.newproject.page;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.java.newproject.service.EmployeePageService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeePageController {

//    private final EmployeePageService service;
//
//    @GetMapping
//    public String getAllEmployees(Model model) {
//        List<EmployeePageDto> employees = service.findAll();
//        model.addAttribute("employees", employees);
//        return "employees-page";
//    }
//
//    @GetMapping("/{id}")
//    public String getEmployeePage(@PathVariable Long id, Model model) {
//        Optional<EmployeePageDto> employeeOpt = service.findById(id);
//        if(employeeOpt.isEmpty()) {
//            return "not-found";
//        }
//        model.addAttribute("employee", employeeOpt.get());
//        return "employee-page";
//    }
}
