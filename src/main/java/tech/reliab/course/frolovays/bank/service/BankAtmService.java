package tech.reliab.course.frolovays.bank.service;

import tech.reliab.course.frolovays.bank.entity.BankAtm;
import tech.reliab.course.frolovays.bank.model.BankAtmRequest;
import tech.reliab.course.frolovays.bank.web.dto.BankAtmDto;

import java.util.List;

public interface BankAtmService {
    BankAtmDto createBankAtm(BankAtmRequest bankAtmRequest);

    BankAtm getBankAtmById(int id);

    BankAtmDto getBankAtmDtoById(int id);

    List<BankAtmDto> getAllBankAtms();

    List<BankAtmDto> getAllBankAtmsByBankId(int bankId);

    BankAtmDto updateBankAtm(int id, String name);

    void deleteBankAtm(int id);
}
