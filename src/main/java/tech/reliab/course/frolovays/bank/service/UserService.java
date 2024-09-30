package tech.reliab.course.frolovays.bank.service;

import tech.reliab.course.frolovays.bank.entity.User;
import tech.reliab.course.frolovays.bank.model.UserRequest;
import tech.reliab.course.frolovays.bank.web.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserRequest userRequest);

    User getUserById(int id);

    UserDto getUserDtoById(int id);

    List<UserDto> getAllUsers();

    UserDto updateUser(int id, String name);

    void deleteUser(int id);
}
