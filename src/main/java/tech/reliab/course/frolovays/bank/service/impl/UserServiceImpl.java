package tech.reliab.course.frolovays.bank.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.reliab.course.frolovays.bank.entity.User;
import tech.reliab.course.frolovays.bank.model.UserRequest;
import tech.reliab.course.frolovays.bank.repository.UserRepository;
import tech.reliab.course.frolovays.bank.service.UserService;
import tech.reliab.course.frolovays.bank.service.mapper.UserMapper;
import tech.reliab.course.frolovays.bank.web.dto.UserDto;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private static final int MONTHLY_INCOME_BOUND = 10001;
    private static final double DIVIDER = 1000.0;
    private static final int FACTOR = 100;

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    /**
     * Создание нового пользователя.
     *
     * @param userRequest содержит данные пользователя
     * @return Созданный пользователь.
     */
    public UserDto createUser(UserRequest userRequest) {
        User user = new User(userRequest.getFullName(), userRequest.getBirthDate(), userRequest.getJob());
        user.setMonthlyIncome(generateMonthlyIncome());
        user.setCreditRating(generateCreditRating(user.getMonthlyIncome()));
        return userMapper.toDto(userRepository.save(user));
    }

    /**
     * Генерация случайного месячного дохода пользователя.
     *
     * @return Случайный месячный доход.
     */
    private int generateMonthlyIncome() {
        return new Random().nextInt(MONTHLY_INCOME_BOUND);
    }

    /**
     * Генерация кредитного рейтинга пользователя,
     * основанного на его месячном доходе.
     *
     * @param monthlyIncome Месячный доход пользователя.
     * @return Кредитный рейтинг пользователя.
     */
    private int generateCreditRating(double monthlyIncome) {
        return (int) Math.ceil(monthlyIncome / DIVIDER) * FACTOR;
    }

    /**
     * Чтение пользователя по его идентификатору.
     *
     * @param id Идентификатор пользователя.
     * @return Пользователь, если он найден
     * @throws NoSuchElementException Если пользователь не найден.
     */
    public User getUserById(int id) {
        return userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User was not found"));
    }

    public UserDto getUserDtoById(int id) {
        return userMapper.toDto(getUserById(id));
    }

    /**
     * Чтение всех пользователей.
     *
     * @return Список всех пользователей.
     */
    public List<UserDto> getAllUsers() {
        return userMapper.toDtoList(userRepository.findAll());
    }

    /**
     * Обновление информации о пользователе по его идентификатору.
     *
     * @param id   Идентификатор пользователя.
     * @param name Новое имя пользователя.
     */
    public UserDto updateUser(int id, String name) {
        User user = getUserById(id);
        user.setFullName(name);
        return userMapper.toDto(userRepository.save(user));
    }

    /**
     * Удаление пользователя по его идентификатору.
     *
     * @param id Идентификатор пользователя.
     */
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}
