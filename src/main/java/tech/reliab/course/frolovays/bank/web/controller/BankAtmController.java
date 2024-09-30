package tech.reliab.course.frolovays.bank.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import tech.reliab.course.frolovays.bank.model.BankAtmRequest;
import tech.reliab.course.frolovays.bank.web.dto.BankAtmDto;

import java.util.List;

public interface BankAtmController {

    @Operation(summary = "Создание банкомата")
    @ApiResponse(responseCode = "201", description = "Банкомат успешно создан",
            content = @Content(schema = @Schema(implementation = BankAtmDto.class)))
    ResponseEntity<BankAtmDto> createBankAtm(@RequestBody BankAtmRequest bankAtmRequest);

    @Operation(summary = "Удаление банкомата")
    @ApiResponse(responseCode = "204", description = "Банкомат успешно удален")
    ResponseEntity<Void> deleteBankAtm(@PathVariable int id);

    @Operation(summary = "Изменение названия банкомата")
    @ApiResponse(responseCode = "200", description = "Банкомат успешно изменен",
            content = @Content(schema = @Schema(implementation = BankAtmDto.class)))
    ResponseEntity<BankAtmDto> updateBankAtm(@PathVariable int id, @RequestParam(name = "name") String name);

    @Operation(summary = "Получение банкомата по id")
    @ApiResponse(responseCode = "200", description = "Банкомат успешно получен")
    ResponseEntity<BankAtmDto> getBankAtmById(@PathVariable int id);

    @Operation(summary = "Получение банкоматов по id банка")
    @ApiResponse(responseCode = "200", description = "Банкоматы успешно получены")
    ResponseEntity<List<BankAtmDto>> getAllBankAtmByBankId(@PathVariable int bankId);

    @Operation(summary = "Получение всех банкоматов")
    @ApiResponse(responseCode = "200", description = "Банкомат успешно получены")
    ResponseEntity<List<BankAtmDto>> getAllBankAtms();
}
