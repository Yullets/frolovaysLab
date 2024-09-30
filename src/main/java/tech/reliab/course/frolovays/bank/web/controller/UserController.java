package tech.reliab.course.frolovays.bank.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import tech.reliab.course.frolovays.bank.model.UserRequest;
import tech.reliab.course.frolovays.bank.web.dto.BankDto;
import tech.reliab.course.frolovays.bank.web.dto.UserDto;

import java.util.List;

public interface UserController {

    @Operation(summary = "Создание юзера")
    @ApiResponse(responseCode = "201", description = "Юзер успешно создан",
            content = @Content(schema = @Schema(implementation = BankDto.class)))
    ResponseEntity<UserDto> createUser(@RequestBody UserRequest userRequest);

    @Operation(summary = "Удаление юзера")
    @ApiResponse(responseCode = "204", description = "Юзер успешно удален")
    ResponseEntity<Void> deleteUser(@PathVariable int id);

    @Operation(summary = "Изменение имени юзера")
    @ApiResponse(responseCode = "200", description = "Юзер успешно изменен",
            content = @Content(schema = @Schema(implementation = BankDto.class)))
    ResponseEntity<UserDto> updateUser(@PathVariable int id, @RequestParam(name = "name") String name);

    @Operation(summary = "Получение юзера по id")
    @ApiResponse(responseCode = "200", description = "Юзер успешно получен")
    ResponseEntity<UserDto> getUserById(@PathVariable int id);

    @Operation(summary = "Получение всех юзеров")
    @ApiResponse(responseCode = "200", description = "Юзеры успешно получены")
    ResponseEntity<List<UserDto>> getAllUsers();
}
