package tech.reliab.course.frolovays.bank.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.reliab.course.frolovays.bank.entity.Bank;
import tech.reliab.course.frolovays.bank.entity.BankOffice;
import tech.reliab.course.frolovays.bank.entity.BankOfficeStatus;
import tech.reliab.course.frolovays.bank.model.BankOfficeRequest;
import tech.reliab.course.frolovays.bank.repository.BankOfficeRepository;
import tech.reliab.course.frolovays.bank.service.BankOfficeService;
import tech.reliab.course.frolovays.bank.service.BankService;
import tech.reliab.course.frolovays.bank.service.mapper.BankOfficeMapper;
import tech.reliab.course.frolovays.bank.web.dto.BankOfficeDto;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class BankOfficeServiceImpl implements BankOfficeService {

    private final BankOfficeRepository bankOfficeRepository;
    private final BankService bankService;
    private final BankOfficeMapper bankOfficeMapper;


    /**
     * Создание нового офиса банка.
     *
     * @param bankOfficeRequest содержит данные про офис
     * @return Созданный офис банка.
     */
    public BankOfficeDto createBankOffice(BankOfficeRequest bankOfficeRequest) {
        Bank bank = bankService.getBankById(bankOfficeRequest.getBankId());
        BankOffice bankOffice = new BankOffice(bankOfficeRequest.getName(), bankOfficeRequest.getAddress(),
                bankOfficeRequest.isCanPlaceAtm(), bankOfficeRequest.isCanIssueLoan(),
                bankOfficeRequest.isCashWithdrawal(), bankOfficeRequest.isCashDeposit(),
                bankOfficeRequest.getRentCost(), bank);
        bankOffice.setStatus(BankOfficeStatus.randomStatus());
        bankOffice.setOfficeMoney(generateOfficeMoney(bank));
        return bankOfficeMapper.toDto(bankOfficeRepository.save(bankOffice));
    }

    /**
     * Генерация случайного количества денег в офисе банка.
     *
     * @param bank Банк, которому принадлежит офис.
     * @return Случайное количество денег в офисе банка.
     */
    private double generateOfficeMoney(Bank bank) {
        return new Random().nextDouble(bank.getTotalMoney());
    }

    /**
     * Поиск офиса банка по его идентификатору.
     *
     * @param id Идентификатор офиса банка.
     * @return Офис банка, если он найден
     * @throws NoSuchElementException Если офис не найден.
     */
    public BankOffice getBankOfficeById(int id) {
        return bankOfficeRepository.findById(id).orElseThrow(() -> new NoSuchElementException("BankOffice was not found"));
    }

    public BankOfficeDto getBankDtoOfficeById(int id) {
        return bankOfficeMapper.toDto(getBankOfficeById(id));
    }

    /**
     * Чтение всех офисов банка.
     *
     * @return Список всех офисов банка.
     */
    public List<BankOfficeDto> getAllBankOffices() {
        return bankOfficeMapper.toDtoList(bankOfficeRepository.findAll());
    }

    /**
     * Обновление информации об офисе банка по его идентификатору.
     *
     * @param id   Идентификатор офиса банка.
     * @param name Новое название офиса банка.
     */
    public BankOfficeDto updateBankOffice(int id, String name) {
        BankOffice bankOffice = getBankOfficeById(id);
        bankOffice.setName(name);
        return bankOfficeMapper.toDto(bankOfficeRepository.save(bankOffice));
    }

    /**
     * Удаление офиса банка по его идентификатору
     *
     * @param id Идентификатор офиса банка.
     */
    public void deleteBankAtm(int id) {
        bankOfficeRepository.deleteById(id);
    }
}
