package tech.reliab.course.frolovays.bank.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import tech.reliab.course.frolovays.bank.model.CreditAccountRequest;
import tech.reliab.course.frolovays.bank.web.dto.BankDto;
import tech.reliab.course.frolovays.bank.web.dto.CreditAccountDto;

import java.util.List;

public interface CreditAccountController {

    @Operation(summary = "Создание кредитного аккаунта")
    @ApiResponse(responseCode = "201", description = "Кредитный аккаунт успешно создан",
            content = @Content(schema = @Schema(implementation = BankDto.class)))
    ResponseEntity<CreditAccountDto> createCreditAccount(@RequestBody CreditAccountRequest creditAccountRequest);

    @Operation(summary = "Удаление кредитного аккаунта")
    @ApiResponse(responseCode = "204", description = "Кредитный аккаунт успешно удален")
    ResponseEntity<Void> deleteCreditAccount(@PathVariable int id);

    @Operation(summary = "Изменение названия кредитного аккаунта")
    @ApiResponse(responseCode = "200", description = "Кредитный аккаунт успешно изменен",
            content = @Content(schema = @Schema(implementation = BankDto.class)))
    ResponseEntity<CreditAccountDto> updateCreditAccount(@PathVariable int id, @RequestParam(name = "bankId") int bankId);

    @Operation(summary = "Получение кредитного аккаунта по id")
    @ApiResponse(responseCode = "200", description = "Кредитный аккаунт успешно получен")
    ResponseEntity<CreditAccountDto> getBankByCreditAccount(@PathVariable int id);

    @Operation(summary = "Получение всех кредитных аккаунтов")
    @ApiResponse(responseCode = "200", description = "Кредитные аккаунты успешно получены")
    ResponseEntity<List<CreditAccountDto>> getAllCreditAccounts();
}
