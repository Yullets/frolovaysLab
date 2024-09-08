package tech.reliab.course.frolovays.bank.service.impl;

import tech.reliab.course.frolovays.bank.entity.Bank;
import tech.reliab.course.frolovays.bank.service.BankService;
import tech.reliab.course.frolovays.bank.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class BankServiceImpl implements BankService {

    private static final int RATING_BOUND = 101;
    private static final int TOTAL_MONEY_BOUND = 1000001;
    private static final int MAX_RATE = 20;
    private static final double DIVIDER = 10.0;
    private static int banksCount = 0;

    private final UserService userService;

    private List<Bank> banks = new ArrayList<>();

    public BankServiceImpl(UserService userService) {
        this.userService = userService;
    }

    // создание банка (Crud)
    public Bank createBank(String bankName) {
        Bank bank = new Bank(bankName);
        bank.setId(banksCount++);
        bank.setRating(generateRating());
        bank.setTotalMoney(generateTotalMoney());
        bank.setInterestRate(calculateInterestRate(bank.getRating()));
        banks.add(bank);
        return bank;
    }

    // генерация рейтинга банка
    private int generateRating() {
        return new Random().nextInt(RATING_BOUND);
    }

    // генерация всех денег банка
    private double generateTotalMoney() {
        return new Random().nextInt(TOTAL_MONEY_BOUND);
    }

    // вычисление процентной ставки. ставка не должна быть выше 20%,
    // чем выше рейтинг банка, тем ниже ставка
    private double calculateInterestRate(int rating) {
        return MAX_RATE - (rating / DIVIDER);
    }

    // чтение банка (cRud)
    public Optional<Bank> getBankById(int id) {
        return banks.stream()
                .filter(bank -> bank.getId() == id)
                .findFirst();
    }

    // чтение всех банков (cRud)
    public List<Bank> getAllBanks() {
        return new ArrayList<>(banks);
    }

    // обновление банка по id (crUd)
    public void updateBank(int id, String name) {
        Bank bank = getBankIfExists(id);
        bank.setName(name);
    }

    // удаление банка по id (cruD)
    public void deleteBank(int id) {
        Bank bank = getBankIfExists(id);
        banks.remove(bank);
        userService.deleteBank(bank);
    }

    // получение банка по id, если он существует,
    // иначе - проброс ошибки
    public Bank getBankIfExists(int id) {
        Optional<Bank> bankOptional = getBankById(id);
        if(bankOptional.isEmpty()) {
            throw new RuntimeException("Bank was not found");
        }
        return bankOptional.get();
    }

    // Увеличение количества офисов
    public void addOffice(Bank bank) {
        bank.setOfficeCount(bank.getOfficeCount() + 1);
    }

    // Увеличение количества банкоматов
    public void addAtm(Bank bank) {
        bank.setAtmCount(bank.getAtmCount() + 1);
    }

    // Увеличение количества сотрудников
    public void addEmployee(Bank bank) {
        bank.setEmployeeCount(bank.getEmployeeCount() + 1);
    }

    // Увеличение количества клиентов
    public void addClient(Bank bank) {
        bank.setClientCount(bank.getClientCount() + 1);
    }

    // Уменьшение количества офисов
    public void removeOffice(Bank bank) {
        bank.setOfficeCount(bank.getOfficeCount() - 1);
    }

    // Уменьшение количества банкоматов
    public void removeAtm(Bank bank) {
        bank.setAtmCount(bank.getAtmCount() - 1);
    }

    // Уменьшение количества работников
    public void removeEmployee(Bank bank) {
        bank.setEmployeeCount(bank.getEmployeeCount() - 1);
    }

    // Уменьшение количества клиентов
    public void removeClient(Bank bank) {
        bank.setClientCount(bank.getClientCount() - 1);
    }
}
