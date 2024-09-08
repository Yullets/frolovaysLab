package tech.reliab.course.frolovays.bank.service.impl;

import tech.reliab.course.frolovays.bank.entity.Bank;
import tech.reliab.course.frolovays.bank.entity.BankOffice;
import tech.reliab.course.frolovays.bank.entity.BankOfficeStatus;
import tech.reliab.course.frolovays.bank.service.BankOfficeService;
import tech.reliab.course.frolovays.bank.service.BankService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class BankOfficeServiceImpl implements BankOfficeService {

    private static int bankOfficesCount = 0;

    private List<BankOffice> bankOffices = new ArrayList<>();

    private final BankService bankService;

    public BankOfficeServiceImpl(BankService bankService) {
        this.bankService = bankService;
    }

    // создание офиса банка (Crud)
    public BankOffice createBankOffice(String name, String address, boolean canPlaceAtm,
                                       boolean canIssueLoan, boolean cashWithdrawal, boolean cashDeposit,
                                       double rentCost, Bank bank) {
        BankOffice bankOffice = new BankOffice(name, address, canPlaceAtm, canIssueLoan,
                cashWithdrawal, cashDeposit, rentCost, bank);
        bankOffice.setId(bankOfficesCount++);
        bankOffice.setStatus(generateStatus());
        bankOffice.setOfficeMoney(generateOfficeMoney(bank));
        bankOffices.add(bankOffice);
        bankService.addOffice(bank);
        return bankOffice;
    }

    // генерация статуса офиса
    private BankOfficeStatus generateStatus() {
        return BankOfficeStatus.randomStatus();
    }

    // генерация количества денег в офисе
    private double generateOfficeMoney(Bank bank) {
        return new Random().nextDouble(bank.getTotalMoney());
    }

    // поиск офиса по id (cRud)
    public Optional<BankOffice> getBankOfficeById(int id) {
        return bankOffices.stream()
                .filter(bankOffice -> bankOffice.getId() == id)
                .findFirst();
    }

    // чтение всех офисов (cRud)
    public List<BankOffice> getAllBankOffices() {
        return new ArrayList<>(bankOffices);
    }

    // обновление офиса по id (crUd)
    public void updateBankOffice(int id, String name) {
        BankOffice bankOffice = getBankOfficeIfExists(id);
        bankOffice.setName(name);
    }

    // удаление офиса по id (cruD)
    public void deleteBankAtm(int officeId, int bankId) {
        BankOffice bankOffice = getBankOfficeIfExists(officeId);
        bankOffices.remove(bankOffice);
        Bank bank = bankService.getBankIfExists(bankId);
        bankService.removeOffice(bank);
    }

    // получение офиса по id, если он существует,
    // иначе - проброс ошибки
    private BankOffice getBankOfficeIfExists(int id) {
        Optional<BankOffice> bankOfficeOptional = getBankOfficeById(id);
        if(bankOfficeOptional.isEmpty()) {
            throw new RuntimeException("BankOffice was not found");
        }
        return bankOfficeOptional.get();
    }
}
