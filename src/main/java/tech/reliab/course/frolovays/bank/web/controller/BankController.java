package tech.reliab.course.frolovays.bank.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import tech.reliab.course.frolovays.bank.web.dto.BankDto;

import java.util.List;

public interface BankController {

    @Operation(summary = "Создание банка")
    @ApiResponse(responseCode = "201", description = "Банк успешно создан",
            content = @Content(schema = @Schema(implementation = BankDto.class)))
    ResponseEntity<BankDto> createBank(@RequestParam(name = "bankName") String bankName);

    @Operation(summary = "Удаление банка")
    @ApiResponse(responseCode = "204", description = "Банк успешно удален")
    ResponseEntity<Void> deleteBank(@PathVariable int id);

    @Operation(summary = "Изменение названия банка")
    @ApiResponse(responseCode = "200", description = "Банк успешно изменен",
            content = @Content(schema = @Schema(implementation = BankDto.class)))
    ResponseEntity<BankDto> updateBank(@PathVariable int id, @RequestParam(name = "bankName") String bankName);

    @Operation(summary = "Получение банка по id")
    @ApiResponse(responseCode = "200", description = "Банк успешно получен")
    ResponseEntity<BankDto> getBankById(@PathVariable int id);

    @Operation(summary = "Получение всех банков")
    @ApiResponse(responseCode = "200", description = "Банки успешно получены")
    ResponseEntity<List<BankDto>> getAllBanks();
}
