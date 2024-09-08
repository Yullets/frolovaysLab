package tech.reliab.course.frolovays.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tech.reliab.course.frolovays.bank.entity.Bank;
import tech.reliab.course.frolovays.bank.entity.BankAtm;
import tech.reliab.course.frolovays.bank.entity.BankOffice;
import tech.reliab.course.frolovays.bank.entity.CreditAccount;
import tech.reliab.course.frolovays.bank.entity.Employee;
import tech.reliab.course.frolovays.bank.entity.PaymentAccount;
import tech.reliab.course.frolovays.bank.entity.User;
import tech.reliab.course.frolovays.bank.service.BankAtmService;
import tech.reliab.course.frolovays.bank.service.BankOfficeService;
import tech.reliab.course.frolovays.bank.service.BankService;
import tech.reliab.course.frolovays.bank.service.CreditAccountService;
import tech.reliab.course.frolovays.bank.service.EmployeeService;
import tech.reliab.course.frolovays.bank.service.PaymentAccountService;
import tech.reliab.course.frolovays.bank.service.UserService;
import tech.reliab.course.frolovays.bank.service.impl.BankAtmServiceImpl;
import tech.reliab.course.frolovays.bank.service.impl.BankOfficeServiceImpl;
import tech.reliab.course.frolovays.bank.service.impl.BankServiceImpl;
import tech.reliab.course.frolovays.bank.service.impl.CreditAccountServiceImpl;
import tech.reliab.course.frolovays.bank.service.impl.EmployeeServiceImpl;
import tech.reliab.course.frolovays.bank.service.impl.PaymentAccountServiceImpl;
import tech.reliab.course.frolovays.bank.service.impl.UserServiceImpl;

import java.time.LocalDate;

@SpringBootApplication
public class FrolovaysLabApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrolovaysLabApplication.class, args);

        UserService userService = new UserServiceImpl();
        User user = userService.createUser("Смирнова Елена Анатольевна", LocalDate.now(), "Программист");

        BankService bankService = new BankServiceImpl(userService);
        Bank bank = bankService.createBank("T-банк");

        BankOfficeService bankOfficeService = new BankOfficeServiceImpl(bankService);
        BankOffice bankOffice = bankOfficeService.createBankOffice("Офис", "Костюкова, 36", true, true,
                true, true, 500, bank);

        EmployeeService employeeService = new EmployeeServiceImpl(bankService);
        Employee employee = employeeService.createEmployee("Иванова Ирина Григорьевна", LocalDate.now(),
                "Кассир", bank, false, bankOffice, true, 30000);

        BankAtmService bankAtmService = new BankAtmServiceImpl(bankService);
        BankAtm bankAtm = bankAtmService.createBankAtm("Банкомат", "Костюкова, 36", bank, bankOffice, employee,
                true, true, 500);

        PaymentAccountService paymentAccountService = new PaymentAccountServiceImpl(userService, bankService);
        PaymentAccount paymentAccount = paymentAccountService.createPaymentAccount(user, bank);

        CreditAccountService creditAccountService = new CreditAccountServiceImpl(userService, bankService);
        CreditAccount creditAccount = creditAccountService.createCreditAccount(user, bank, LocalDate.now(), 8,
                500000, 14, employee, paymentAccount);

        System.out.println(bank);
        System.out.println(user);
        System.out.println(bankOffice);
        System.out.println(employee);
        System.out.println(bankAtm);
        System.out.println(paymentAccount);
        System.out.println(creditAccount);
    }
}
