package ru.geekbrains.java.newproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class EmployeePageService {

//    private final EmployeeService service;
//
//    public Optional<EmployeePageDto> findById(Long id) {
//        return service.getEmployeeById(id)
//                .map(this::convert);
//    }
//
//    public EmployeePageDto convert(Employee employee) {
//        EmployeePageDto dto = new EmployeePageDto();
//        dto.setId(String.valueOf(employee.getId()));
//        dto.setFirstName(employee.getFirstName());
//        dto.setLastName(employee.getLastName());
//        dto.setAge(String.valueOf(employee.getAge()));
//
//        return dto;
//    }
//
//    public List<EmployeePageDto> findAll()  {
//        return service.getAllEmployees().stream()
//                .map(this::convert)
//                .toList();
//    }
}
