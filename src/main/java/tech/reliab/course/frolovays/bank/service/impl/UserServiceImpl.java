package tech.reliab.course.frolovays.bank.service.impl;

import tech.reliab.course.frolovays.bank.entity.Bank;
import tech.reliab.course.frolovays.bank.entity.CreditAccount;
import tech.reliab.course.frolovays.bank.entity.PaymentAccount;
import tech.reliab.course.frolovays.bank.entity.User;
import tech.reliab.course.frolovays.bank.service.UserService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class UserServiceImpl implements UserService {

    private static final int MONTHLY_INCOME_BOUND = 10001;
    private static final double DIVIDER = 1000.0;
    private static final int FACTOR = 100;
    private static int usersCount = 0;

    private List<User> users = new ArrayList<>();

    // создание юзера (Crud)
    public User createUser(String fullName, LocalDate birthDate, String job) {
        User user = new User(fullName, birthDate, job);
        user.setId(usersCount++);
        user.setMonthlyIncome(generateMonthlyIncome());
        user.setCreditRating(generateCreditRating(user.getMonthlyIncome()));
        users.add(user);
        return user;
    }

    // генерация месячного дохода
    private int generateMonthlyIncome() {
        return new Random().nextInt(MONTHLY_INCOME_BOUND);
    }

    // генерация кредитного рейтинга
    private int generateCreditRating(double monthlyIncome) {
        return (int) Math.ceil(monthlyIncome / DIVIDER) * FACTOR;
    }

    // чтение юзера (cRud)
    public Optional<User> getUserById(int id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst();
    }

    // чтение всех юзеров (cRud)
    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }

    // обновление юзера по id (crUd)
    public void updateUser(int id, String name) {
        User user = getUserIfExists(id);
        user.setFullName(name);
    }

    // удаление юзера по id (cruD)
    public void deleteUser(int id) {
        users.remove(getUserIfExists(id));
    }

    // получение юзера по id, если он существует,
    // иначе - проброс ошибки
    public User getUserIfExists(int id) {
        Optional<User> userOptional = getUserById(id);
        if(userOptional.isEmpty()) {
            throw new RuntimeException("User was not found");
        }
        return userOptional.get();
    }

    // добавление кредитного аккаунта
    public void addCreditAccount(CreditAccount creditAccount, User user) {
        List<CreditAccount> creditAccounts = user.getCreditAccounts();
        creditAccounts.add(creditAccount);
        user.setCreditAccounts(creditAccounts);
    }

    // добавление платежного аккаунта
    public void addPaymentAccount(PaymentAccount paymentAccount, User user) {
        List<PaymentAccount> paymentAccounts = user.getPaymentAccounts();
        paymentAccounts.add(paymentAccount);
        user.setPaymentAccounts(paymentAccounts);
    }

    // добавление банка
    public void addBank(Bank bank, User user) {
        List<Bank> banks = user.getBanks();
        banks.add(bank);
        user.setBanks(banks);
    }

    // удаление кредитного аккаунта
    public void deleteCreditAccount(CreditAccount creditAccount, User user) {
        List<CreditAccount> creditAccounts = user.getCreditAccounts();
        creditAccounts.remove(creditAccount);
        user.setCreditAccounts(creditAccounts);
    }

    // удаление платежного аккаунта
    public void deletePaymentAccount(PaymentAccount paymentAccount, User user) {
        List<PaymentAccount> paymentAccounts = user.getPaymentAccounts();
        paymentAccounts.remove(paymentAccount);
        user.setPaymentAccounts(paymentAccounts);
    }

    // удаление банка
    public void deleteBank(Bank bank) {
        for(User curUser: users) {
            List<Bank> banks = curUser.getBanks();
            banks.remove(bank);
            curUser.setBanks(banks);
        }
    }
}
