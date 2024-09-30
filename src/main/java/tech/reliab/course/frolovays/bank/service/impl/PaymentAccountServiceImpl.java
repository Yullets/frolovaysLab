package tech.reliab.course.frolovays.bank.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.reliab.course.frolovays.bank.entity.PaymentAccount;
import tech.reliab.course.frolovays.bank.model.PaymentAccountRequest;
import tech.reliab.course.frolovays.bank.repository.PaymentAccountRepository;
import tech.reliab.course.frolovays.bank.service.BankService;
import tech.reliab.course.frolovays.bank.service.PaymentAccountService;
import tech.reliab.course.frolovays.bank.service.UserService;
import tech.reliab.course.frolovays.bank.service.mapper.PaymentAccountMapper;
import tech.reliab.course.frolovays.bank.web.dto.PaymentAccountDto;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class PaymentAccountServiceImpl implements PaymentAccountService {

    private final PaymentAccountRepository paymentAccountRepository;
    private final UserService userService;
    private final BankService bankService;
    private final PaymentAccountMapper paymentAccountMapper;

    /**
     * Создание нового платежного аккаунта.
     *
     * @param paymentAccountRequest содержит информацию о userId и bankId
     * @return Созданный платежный аккаунт.
     */
    public PaymentAccountDto createPaymentAccount(PaymentAccountRequest paymentAccountRequest) {
        PaymentAccount paymentAccount = new PaymentAccount(userService.getUserById(paymentAccountRequest.getUserId()),
                bankService.getBankById(paymentAccountRequest.getBankId()));
        return paymentAccountMapper.toDto(paymentAccountRepository.save(paymentAccount));
    }

    /**
     * Чтение платежного аккаунта по его идентификатору.
     *
     * @param id Идентификатор платежного аккаунта.
     * @return Платежный аккаунт
     * @throws NoSuchElementException Если платежный аккаунт не найден.
     */
    public PaymentAccount getPaymentAccountById(int id) {
        return paymentAccountRepository.findById(id).orElseThrow(() -> new NoSuchElementException("PaymentAccount was not found"));
    }

    public PaymentAccountDto getPaymentAccountDtoById(int id) {
        return paymentAccountMapper.toDto(getPaymentAccountById(id));
    }
    /**
     * Чтение всех платежных аккаунтов.
     *
     * @return Список всех платежных аккаунтов.
     */
    public List<PaymentAccountDto> getAllPaymentAccounts() {
        return paymentAccountMapper.toDtoList(paymentAccountRepository.findAll());
    }

    /**
     * Обновление информации о платежном аккаунте по его идентификатору.
     *
     * @param id   Идентификатор платежного аккаунта.
     * @param bankId id банка, в котором открыт аккаунт.
     */
    public PaymentAccountDto updatePaymentAccount(int id, int bankId) {
        PaymentAccount paymentAccount = getPaymentAccountById(id);
        paymentAccount.setBank(bankService.getBankById(bankId));
        return paymentAccountMapper.toDto(paymentAccountRepository.save(paymentAccount));
    }

    /**
     * Удаление платежного аккаунта по его идентификатору.
     *
     * @param id Идентификатор платежного аккаунта.
     */
    public void deletePaymentAccount(int id) {
        paymentAccountRepository.deleteById(id);
    }
}
