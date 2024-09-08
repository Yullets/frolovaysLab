package tech.reliab.course.frolovays.bank.service.impl;

import tech.reliab.course.frolovays.bank.entity.Bank;
import tech.reliab.course.frolovays.bank.entity.CreditAccount;
import tech.reliab.course.frolovays.bank.entity.Employee;
import tech.reliab.course.frolovays.bank.entity.PaymentAccount;
import tech.reliab.course.frolovays.bank.entity.User;
import tech.reliab.course.frolovays.bank.service.BankService;
import tech.reliab.course.frolovays.bank.service.CreditAccountService;
import tech.reliab.course.frolovays.bank.service.UserService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CreditAccountServiceImpl implements CreditAccountService {

    private static int creditAccountsCount = 0;

    private final UserService userService;
    private final BankService bankService;

    private List<CreditAccount> creditAccounts = new ArrayList<>();

    public CreditAccountServiceImpl(UserService userService, BankService bankService) {
        this.userService = userService;
        this.bankService = bankService;
    }

    // создание кредитного аккаунта (Crud)
    public CreditAccount createCreditAccount(User user, Bank bank, LocalDate startDate, int loanTermMonths,
                                             double loanAmount, double interestRate, Employee employee,
                                             PaymentAccount paymentAccount) {
        CreditAccount creditAccount = new CreditAccount(user, bank, startDate, loanTermMonths,
                interestRate, employee, paymentAccount);
        creditAccount.setId(creditAccountsCount++);
        creditAccount.setEndDate(calculateEndDate(startDate, loanTermMonths));
        creditAccount.setLoanAmount(calculateLoanAmount(loanAmount, bank));
        creditAccount.setMonthlyPayment(calculateMonthlyPayment(interestRate, loanAmount, loanTermMonths));
        creditAccount.setInterestRate(calculateInterestRate(interestRate, bank));
        creditAccounts.add(creditAccount);
        userService.addCreditAccount(creditAccount, user);
        return creditAccount;
    }

    // вычисление даты окончания кредита
    private LocalDate calculateEndDate(LocalDate startDate, int loanTermMonths) {
        return startDate.plusMonths(loanTermMonths);
    }

    // расчет аннуитетного платежа
    private double calculateMonthlyPayment(double interestRate, double loanAmount, int loanTermMonths) {
        double monthlyRate = interestRate / 12 / 100;
        return loanAmount * (monthlyRate / (1 - Math.pow(1 + monthlyRate, -loanTermMonths)));
    }

    private double calculateLoanAmount(double loanAmount, Bank bank) {
        if (loanAmount > bank.getTotalMoney()) {
            loanAmount = bank.getTotalMoney();
        }
        return loanAmount;
    }

    // рассчет кредитного рейтинга
    private double calculateInterestRate(double interestRate, Bank bank) {
        if (interestRate > bank.getInterestRate()) {
            System.out.println("Заданная процентная ставка превышает процентную ставку банка. Ставка будет скорректирована.");
            interestRate = bank.getInterestRate();
        }
        return interestRate;
    }

    // чтение аккаунта (cRud)
    public Optional<CreditAccount> getCreditAccountById(int id) {
        return creditAccounts.stream()
                .filter(creditAccount -> creditAccount.getId() == id)
                .findFirst();
    }

    // чтение всех аккаунтов (cRud)
    public List<CreditAccount> getAllCreditAccounts() {
        return new ArrayList<>(creditAccounts);
    }

    // обновление аккаунта по id (crUd)
    public void updateCreditAccount(int id, Bank bank) {
        CreditAccount creditAccount = getCreditAccountIfExists(id);
        creditAccount.setBank(bank);
    }

    // удаление аккаунта по id (cruD)
    public void deleteCreditAccount(int accountId, int userId) {
        CreditAccount creditAccount = getCreditAccountIfExists(accountId);
        creditAccounts.remove(creditAccount);
        User user = userService.getUserIfExists(userId);
        userService.deleteCreditAccount(creditAccount, user);
    }

    // получение аккаунта по id, если он существует,
    // иначе - проброс ошибки
    private CreditAccount getCreditAccountIfExists(int id) {
        Optional<CreditAccount> creditAccountOptional = getCreditAccountById(id);
        if(creditAccountOptional.isEmpty()) {
            throw new RuntimeException("CreditAccount was not found");
        }
        return creditAccountOptional.get();
    }
}
