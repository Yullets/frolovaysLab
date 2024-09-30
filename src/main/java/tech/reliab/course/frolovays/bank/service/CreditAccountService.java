package tech.reliab.course.frolovays.bank.service;

import tech.reliab.course.frolovays.bank.entity.CreditAccount;
import tech.reliab.course.frolovays.bank.model.CreditAccountRequest;
import tech.reliab.course.frolovays.bank.web.dto.CreditAccountDto;

import java.util.List;

public interface CreditAccountService {

    CreditAccountDto createCreditAccount(CreditAccountRequest creditAccountRequest);

    CreditAccount getCreditAccountById(int id);

    CreditAccountDto getCreditAccountDtoById(int id);

    List<CreditAccountDto> getAllCreditAccounts();

    CreditAccountDto updateCreditAccount(int id, int bankId);

    void deleteCreditAccount(int id);
}
