package tech.reliab.course.frolovays.bank.web.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.reliab.course.frolovays.bank.model.UserRequest;
import tech.reliab.course.frolovays.bank.service.UserService;
import tech.reliab.course.frolovays.bank.web.controller.UserController;
import tech.reliab.course.frolovays.bank.web.dto.UserDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserControllerImpl implements UserController {

    private final UserService userService;

    @Override
    @PostMapping
    public ResponseEntity<UserDto> createUser(UserRequest userRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userRequest));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(int id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(int id, String name) {
        return ResponseEntity.ok(userService.updateUser(id, name));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(int id) {
        return ResponseEntity.ok(userService.getUserDtoById(id));
    }

    @Override
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
