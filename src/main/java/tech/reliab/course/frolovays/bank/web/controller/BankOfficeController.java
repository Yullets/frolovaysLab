package tech.reliab.course.frolovays.bank.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import tech.reliab.course.frolovays.bank.model.BankOfficeRequest;
import tech.reliab.course.frolovays.bank.web.dto.BankDto;
import tech.reliab.course.frolovays.bank.web.dto.BankOfficeDto;

import java.util.List;

public interface BankOfficeController {

    @Operation(summary = "Создание офиса")
    @ApiResponse(responseCode = "201", description = "Офис успешно создан",
            content = @Content(schema = @Schema(implementation = BankDto.class)))
    ResponseEntity<BankOfficeDto> createBankOffice(@RequestBody BankOfficeRequest bankOfficeRequest);

    @Operation(summary = "Удаление офиса")
    @ApiResponse(responseCode = "204", description = "Офис успешно удален")
    ResponseEntity<Void> deleteBankOffice(@PathVariable int id);

    @Operation(summary = "Изменение названия офиса")
    @ApiResponse(responseCode = "200", description = "Офис успешно изменен",
            content = @Content(schema = @Schema(implementation = BankDto.class)))
    ResponseEntity<BankOfficeDto> updateBankOffice(@PathVariable int id, @RequestParam(name = "name") String name);

    @Operation(summary = "Получение офиса по id")
    @ApiResponse(responseCode = "200", description = "Офис успешно получен")
    ResponseEntity<BankOfficeDto> getBankOfficeById(@PathVariable int id);

    @Operation(summary = "Получение всех офисов")
    @ApiResponse(responseCode = "200", description = "Офисы успешно получены")
    ResponseEntity<List<BankOfficeDto>> getAllBankOffices();
}
