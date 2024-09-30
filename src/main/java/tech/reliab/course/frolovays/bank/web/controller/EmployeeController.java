package tech.reliab.course.frolovays.bank.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import tech.reliab.course.frolovays.bank.model.EmployeeRequest;
import tech.reliab.course.frolovays.bank.web.dto.BankDto;
import tech.reliab.course.frolovays.bank.web.dto.EmployeeDto;

import java.util.List;

public interface EmployeeController {

    @Operation(summary = "Создание работника")
    @ApiResponse(responseCode = "201", description = "Работник успешно создан",
            content = @Content(schema = @Schema(implementation = BankDto.class)))
    ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeRequest employeeRequest);

    @Operation(summary = "Удаление работника")
    @ApiResponse(responseCode = "204", description = "Работник успешно удален")
    ResponseEntity<Void> deleteEmployee(@PathVariable int id);

    @Operation(summary = "Изменение имени работника")
    @ApiResponse(responseCode = "200", description = "Работник успешно изменен",
            content = @Content(schema = @Schema(implementation = BankDto.class)))
    ResponseEntity<EmployeeDto> updateEmployee(@PathVariable int id, @RequestParam(name = "name") String name);

    @Operation(summary = "Получение работника по id")
    @ApiResponse(responseCode = "200", description = "Работник успешно получен")
    ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable int id);

    @Operation(summary = "Получение всех работников")
    @ApiResponse(responseCode = "200", description = "Работники успешно получены")
    ResponseEntity<List<EmployeeDto>> getAllEmployees();
}
