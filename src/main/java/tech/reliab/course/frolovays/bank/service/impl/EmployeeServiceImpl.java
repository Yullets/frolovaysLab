package tech.reliab.course.frolovays.bank.service.impl;

import tech.reliab.course.frolovays.bank.entity.Bank;
import tech.reliab.course.frolovays.bank.entity.BankOffice;
import tech.reliab.course.frolovays.bank.entity.Employee;
import tech.reliab.course.frolovays.bank.service.BankService;
import tech.reliab.course.frolovays.bank.service.EmployeeService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeServiceImpl implements EmployeeService {

    private static int employeesCount = 0;

    private final BankService bankService;

    private List<Employee> employees = new ArrayList<>();

    public EmployeeServiceImpl(BankService bankService) {
        this.bankService = bankService;
    }

    // создание работника (Crud)
    public Employee createEmployee(String fullName, LocalDate birthDate, String position, Bank bank, boolean remoteWork,
                                   BankOffice bankOffice, boolean canIssueLoans, double salary) {
        Employee employee = new Employee(fullName, birthDate, position, bank, remoteWork,
                bankOffice, canIssueLoans, salary);
        employee.setId(employeesCount++);
        employees.add(employee);
        bankService.addEmployee(bank);
        return employee;
    }

    // чтение работника (cRud)
    public Optional<Employee> getEmployeeById(int id) {
        return employees.stream()
                .filter(employee -> employee.getId() == id)
                .findFirst();
    }

    // чтение всех работниов (cRud)
    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees);
    }

    // обновление работника по id (crUd)
    public void updateEmployee(int id, String name) {
        Employee employee = getEmployeeIfExists(id);
        employee.setFullName(name);
    }

    // удаление работника по id (cruD)
    public void deleteEmployee(int id) {
        employees.remove(getEmployeeIfExists(id));
    }

    // получение работника по id, если он существует,
    // иначе - проброс ошибки
    public Employee getEmployeeIfExists(int id) {
        Optional<Employee> employeeOptional = getEmployeeById(id);
        if(employeeOptional.isEmpty()) {
            throw new RuntimeException("EmployeeOptional was not found");
        }
        return employeeOptional.get();
    }
}
