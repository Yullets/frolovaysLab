package tech.reliab.course.frolovays.bank.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.reliab.course.frolovays.bank.entity.Employee;
import tech.reliab.course.frolovays.bank.model.EmployeeRequest;
import tech.reliab.course.frolovays.bank.repository.EmployeeRepository;
import tech.reliab.course.frolovays.bank.service.BankOfficeService;
import tech.reliab.course.frolovays.bank.service.BankService;
import tech.reliab.course.frolovays.bank.service.EmployeeService;
import tech.reliab.course.frolovays.bank.service.mapper.EmployeeMapper;
import tech.reliab.course.frolovays.bank.web.dto.EmployeeDto;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final BankService bankService;
    private final BankOfficeService bankOfficeService;
    private final EmployeeMapper employeeMapper;

    /**
     * Создание нового сотрудника банка.
     *
     * @param employeeRequest информация о сотруднике
     * @return Созданный сотрудник банка.
     */
    public EmployeeDto createEmployee(EmployeeRequest employeeRequest) {
        Employee employee = new Employee(employeeRequest.getFullName(), employeeRequest.getBirthDate(),
                employeeRequest.getPosition(), bankService.getBankById(employeeRequest.getBankId()),
                employeeRequest.isRemoteWork(), bankOfficeService.getBankOfficeById(employeeRequest.getBankOfficeId()),
                employeeRequest.isCanIssueLoans(), employeeRequest.getSalary());
        return employeeMapper.toDto(employeeRepository.save(employee));
    }

    /**
     * Чтение сотрудника по его идентификатору.
     *
     * @param id Идентификатор сотрудника.
     * @return Сотрудник, если он найден
     * @throws NoSuchElementException Если сотрудник не найден.
     */
    public EmployeeDto getEmployeeDtoById(int id) {
        return employeeMapper.toDto(getEmployeeById(id));
    }

    /**
     * Чтение сотрудника по его идентификатору.
     *
     * @param id Идентификатор сотрудника.
     * @return Сотрудник, если он найден
     * @throws NoSuchElementException Если сотрудник не найден.
     */
    public Employee getEmployeeById(int id) {
        return employeeRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Employee was not found"));
    }

    /**
     * Чтение всех сотрудников.
     *
     * @return Список всех сотрудников.
     */
    public List<EmployeeDto> getAllEmployees() {
        return employeeMapper.toDtoList(employeeRepository.findAll());
    }

    /**
     * Обновление информации о сотруднике по его идентификатору.
     *
     * @param id   Идентификатор сотрудника.
     * @param name Новое имя сотрудника.
     */
    public EmployeeDto updateEmployee(int id, String name) {
        Employee employee = getEmployeeById(id);
        employee.setFullName(name);
        return employeeMapper.toDto(employeeRepository.save(employee));
    }

    /**
     * Удаление сотрудника по его идентификатору.
     *
     * @param id Идентификатор сотрудника.
     */
    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }
}
