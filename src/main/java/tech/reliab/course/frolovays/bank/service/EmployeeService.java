package tech.reliab.course.frolovays.bank.service;

import tech.reliab.course.frolovays.bank.entity.Employee;
import tech.reliab.course.frolovays.bank.model.EmployeeRequest;
import tech.reliab.course.frolovays.bank.web.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    EmployeeDto createEmployee(EmployeeRequest employeeRequest);

    EmployeeDto getEmployeeDtoById(int id);

    Employee getEmployeeById(int id);

    List<EmployeeDto> getAllEmployees();

    EmployeeDto updateEmployee(int id, String name);

    void deleteEmployee(int id);
}
