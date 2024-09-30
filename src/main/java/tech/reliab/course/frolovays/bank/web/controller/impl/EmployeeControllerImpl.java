package tech.reliab.course.frolovays.bank.web.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.reliab.course.frolovays.bank.model.EmployeeRequest;
import tech.reliab.course.frolovays.bank.service.EmployeeService;
import tech.reliab.course.frolovays.bank.web.controller.EmployeeController;
import tech.reliab.course.frolovays.bank.web.dto.EmployeeDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
public class EmployeeControllerImpl implements EmployeeController {

    private final EmployeeService employeeService;

    @Override
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(EmployeeRequest employeeRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.createEmployee(employeeRequest));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(int id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(int id, String name) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, name));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(int id) {
        return ResponseEntity.ok(employeeService.getEmployeeDtoById(id));
    }

    @Override
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }
}
