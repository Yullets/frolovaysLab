package tech.reliab.course.frolovays.bank.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import tech.reliab.course.frolovays.bank.model.PaymentAccountRequest;
import tech.reliab.course.frolovays.bank.web.dto.BankDto;
import tech.reliab.course.frolovays.bank.web.dto.PaymentAccountDto;

import java.util.List;

public interface PaymentAccountController {

    @Operation(summary = "Создание платежного аккаунта")
    @ApiResponse(responseCode = "201", description = "Платежный аккаунт успешно создан",
            content = @Content(schema = @Schema(implementation = BankDto.class)))
    ResponseEntity<PaymentAccountDto> createPaymentAccount(@RequestBody PaymentAccountRequest paymentAccountRequest);

    @Operation(summary = "Удаление платежного аккаунта")
    @ApiResponse(responseCode = "204", description = "Платежный аккаунт успешно удален")
    ResponseEntity<Void> deletePaymentAccount(@PathVariable int id);

    @Operation(summary = "Изменение названия платежного аккаунта")
    @ApiResponse(responseCode = "200", description = "Платежный аккаунт успешно изменен",
            content = @Content(schema = @Schema(implementation = BankDto.class)))
    ResponseEntity<PaymentAccountDto> updatePaymentAccount(@PathVariable int id, @RequestParam(name = "bankId") int bankId);

    @Operation(summary = "Получение платежного аккаунта по id")
    @ApiResponse(responseCode = "200", description = "Платежный аккаунт успешно получен")
    ResponseEntity<PaymentAccountDto> getBankByPaymentAccount(@PathVariable int id);

    @Operation(summary = "Получение всех платежных аккаунтов")
    @ApiResponse(responseCode = "200", description = "Платежные аккаунты успешно получены")
    ResponseEntity<List<PaymentAccountDto>> getAllPaymentAccounts();
}
