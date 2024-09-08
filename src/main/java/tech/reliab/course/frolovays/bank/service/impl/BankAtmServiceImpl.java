package tech.reliab.course.frolovays.bank.service.impl;

import tech.reliab.course.frolovays.bank.entity.Bank;
import tech.reliab.course.frolovays.bank.entity.BankAtm;
import tech.reliab.course.frolovays.bank.entity.BankAtmStatus;
import tech.reliab.course.frolovays.bank.entity.BankOffice;
import tech.reliab.course.frolovays.bank.entity.Employee;
import tech.reliab.course.frolovays.bank.service.BankAtmService;
import tech.reliab.course.frolovays.bank.service.BankService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

public class BankAtmServiceImpl implements BankAtmService {

    private static int bankAtmsCount = 0;

    private List<BankAtm> bankAtms = new ArrayList<>();

    private final BankService bankService;

    public BankAtmServiceImpl(BankService bankService) {
        this.bankService = bankService;
    }

    // создание банкомата (Crud)
    public BankAtm createBankAtm(String name, String address, Bank bank, BankOffice location, Employee employee,
                                 boolean cashWithdrawal, boolean cashDeposit, double maintenanceCost) {
        BankAtm bankAtm = new BankAtm(name, address, bank, location, employee,
                cashWithdrawal, cashDeposit, maintenanceCost);
        bankAtm.setId(bankAtmsCount++);
        bankAtm.setStatus(generateStatus());
        bankAtm.setAtmMoney(generateAtmMoney(bank));
        bankService.addAtm(bank);
        bankAtms.add(bankAtm);
        return bankAtm;
    }

    //генерация статуса банкомата
    private BankAtmStatus generateStatus() {
        return BankAtmStatus.randomStatus();
    }

    // генерация количества денег в автомате (лимит - количество денег в банке)
    private double generateAtmMoney(Bank bank) {
        return new Random().nextDouble(bank.getTotalMoney());
    }

    // чтение банкомата (cRud)
    public Optional<BankAtm> getBankAtmById(int id) {
        return bankAtms.stream()
                .filter(bankAtm -> bankAtm.getId() == id)
                .findFirst();
    }

    // чтение всех банкоматов (cRud)
    public List<BankAtm> getAllBankAtms() {
        return new ArrayList<>(bankAtms);
    }

    // чтение всех банкоматов определенного банка (cRud)
    public List<BankAtm> getAllBankAtmsByBank(Bank bank) {
        return bankAtms.stream()
                .filter(bankAtm -> bankAtm.getBank().getId() == bank.getId())
                .collect(Collectors.toList());
    }

    // обновление банкомата по id (crUd)
    public void updateBankAtm(int id, String name) {
        BankAtm bankAtm = getBankAtmIfExists(id);
        bankAtm.setName(name);
    }

    // удаление банкомата по id (cruD)
    public void deleteBankAtm(int id) {
        BankAtm bankAtm = getBankAtmIfExists(id);
        bankAtms.remove(bankAtm);
        Bank bank = bankAtm.getBank();
        bankService.removeAtm(bank);
    }

    // получение банкомата по id, если он существует,
    // иначе - проброс ошибки
    private BankAtm getBankAtmIfExists(int id) {
        Optional<BankAtm> bankAtmOptional = getBankAtmById(id);
        if(bankAtmOptional.isEmpty()) {
            throw new RuntimeException("BankAtm was not found");
        }
        return bankAtmOptional.get();
    }
}
