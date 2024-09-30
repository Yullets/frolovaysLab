package tech.reliab.course.frolovays.bank.service;

import tech.reliab.course.frolovays.bank.entity.BankOffice;
import tech.reliab.course.frolovays.bank.model.BankOfficeRequest;
import tech.reliab.course.frolovays.bank.web.dto.BankOfficeDto;

import java.util.List;

public interface BankOfficeService {

    BankOfficeDto createBankOffice(BankOfficeRequest bankOfficeRequest);

    BankOffice getBankOfficeById(int id);

    BankOfficeDto getBankDtoOfficeById(int id);

    List<BankOfficeDto> getAllBankOffices();

    BankOfficeDto updateBankOffice(int id, String name);

    void deleteBankAtm(int id);
}
