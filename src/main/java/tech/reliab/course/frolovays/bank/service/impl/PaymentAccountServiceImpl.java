package tech.reliab.course.frolovays.bank.service.impl;

import tech.reliab.course.frolovays.bank.entity.Bank;
import tech.reliab.course.frolovays.bank.entity.PaymentAccount;
import tech.reliab.course.frolovays.bank.entity.User;
import tech.reliab.course.frolovays.bank.service.BankService;
import tech.reliab.course.frolovays.bank.service.PaymentAccountService;
import tech.reliab.course.frolovays.bank.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PaymentAccountServiceImpl implements PaymentAccountService {

    private static int paymentAccountCount = 0;

    private final UserService userService;
    private final BankService bankService;

    private List<PaymentAccount> paymentAccounts = new ArrayList<>();

    public PaymentAccountServiceImpl (UserService userService, BankService bankService) {
        this.userService = userService;
        this.bankService = bankService;
    }

    // создание платежного аккаунта (Crud)
    public PaymentAccount createPaymentAccount(User user, Bank bank) {
        PaymentAccount paymentAccount = new PaymentAccount(user, bank);
        paymentAccount.setId(paymentAccountCount++);
        paymentAccounts.add(paymentAccount);
        userService.addPaymentAccount(paymentAccount, user);
        userService.addBank(bank, user);
        bankService.addClient(bank);
        return paymentAccount;
    }

    // чтение платежного аккаунта (cRud)
    public Optional<PaymentAccount> getPaymentAccountById(int id) {
        return paymentAccounts.stream()
                .filter(paymentAccount -> paymentAccount.getId() == id)
                .findFirst();
    }

    // чтение всех платежных аккаунтов (cRud)
    public List<PaymentAccount> getAllPaymentAccounts() {
        return new ArrayList<>(paymentAccounts);
    }

    // обновление платежного аккаунта по id (crUd)
    public void updatePaymentAccount(int id, Bank bank) {
        PaymentAccount paymentAccount = getPaymentAccountIfExists(id);
        paymentAccount.setBank(bank);
    }

    // удаление платежного аккаунта по id (cruD)
    public void deletePaymentAccount(int id) {
        PaymentAccount paymentAccount = getPaymentAccountIfExists(id);
        paymentAccounts.remove(paymentAccount);
        userService.deletePaymentAccount(paymentAccount, paymentAccount.getUser());
    }

    // получение юзера по id, если он существует,
    // иначе - проброс ошибки
    private PaymentAccount getPaymentAccountIfExists(int id) {
        Optional<PaymentAccount> paymentAccountOptional = getPaymentAccountById(id);
        if(paymentAccountOptional.isEmpty()) {
            throw new RuntimeException("PaymentAccount was not found");
        }
        return paymentAccountOptional.get();
    }
}
