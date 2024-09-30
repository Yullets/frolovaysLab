package tech.reliab.course.frolovays.bank.service;

import tech.reliab.course.frolovays.bank.entity.PaymentAccount;
import tech.reliab.course.frolovays.bank.model.PaymentAccountRequest;
import tech.reliab.course.frolovays.bank.web.dto.PaymentAccountDto;

import java.util.List;

public interface PaymentAccountService {

    PaymentAccountDto createPaymentAccount(PaymentAccountRequest paymentAccountRequest);

    PaymentAccount getPaymentAccountById(int id);

    PaymentAccountDto getPaymentAccountDtoById(int id);

    List<PaymentAccountDto> getAllPaymentAccounts();

    PaymentAccountDto updatePaymentAccount(int id, int bankId);

    void deletePaymentAccount(int id);
}
