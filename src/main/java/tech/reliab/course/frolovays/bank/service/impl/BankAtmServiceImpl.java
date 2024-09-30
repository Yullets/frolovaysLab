package tech.reliab.course.frolovays.bank.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.reliab.course.frolovays.bank.entity.Bank;
import tech.reliab.course.frolovays.bank.entity.BankAtm;
import tech.reliab.course.frolovays.bank.entity.BankAtmStatus;
import tech.reliab.course.frolovays.bank.model.BankAtmRequest;
import tech.reliab.course.frolovays.bank.repository.BankAtmRepository;
import tech.reliab.course.frolovays.bank.service.BankAtmService;
import tech.reliab.course.frolovays.bank.service.BankOfficeService;
import tech.reliab.course.frolovays.bank.service.BankService;
import tech.reliab.course.frolovays.bank.service.EmployeeService;
import tech.reliab.course.frolovays.bank.service.mapper.BankAtmMapper;
import tech.reliab.course.frolovays.bank.web.dto.BankAtmDto;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class BankAtmServiceImpl implements BankAtmService {

    private final BankAtmRepository bankAtmRepository;
    private final BankService bankService;
    private final BankOfficeService bankOfficeService;
    private final EmployeeService employeeService;
    private final BankAtmMapper bankAtmMapper;


    /**
     * Создание нового банкомата.
     *
     * @param bankAtmRequest данные о банкомате
     * @return Созданный банкомат.
     */
    public BankAtmDto createBankAtm(BankAtmRequest bankAtmRequest) {
        Bank bank = bankService.getBankById(bankAtmRequest.getBankId());
        BankAtm bankAtm = new BankAtm(bankAtmRequest.getName(), bankAtmRequest.getAddress(), bank,
                bankOfficeService.getBankOfficeById(bankAtmRequest.getLocationId()),
                employeeService.getEmployeeById(bankAtmRequest.getEmployeeId()),
                bankAtmRequest.isCashWithdrawal(), bankAtmRequest.isCashDeposit(), bankAtmRequest.getMaintenanceCost());
        bankAtm.setStatus(BankAtmStatus.randomStatus());
        bankAtm.setAtmMoney(generateAtmMoney(bank));
        return bankAtmMapper.toDto(bankAtmRepository.save(bankAtm));
    }

    /**
     * Генерация случайного количества денег в банкомате.
     *
     * @param bank Банк, которому принадлежит банкомат.
     * @return Случайное количество денег в банкомате.
     */
    private double generateAtmMoney(Bank bank) {
        return new Random().nextDouble(bank.getTotalMoney());
    }

    /**
     * Чтение банкомата по его идентификатору.
     *
     * @param id Идентификатор банкомата.
     * @return Банкомат, если он найден
     * @throws NoSuchElementException Если банкомат не найден.
     */
    public BankAtm getBankAtmById(int id) {
        return bankAtmRepository.findById(id).orElseThrow(() -> new NoSuchElementException("BankAtm was not found"));
    }

    public BankAtmDto getBankAtmDtoById(int id) {
        return bankAtmMapper.toDto(getBankAtmById(id));
    }

    /**
     * Чтение всех банкоматов.
     *
     * @return Список всех банкоматов.
     */
    public List<BankAtmDto> getAllBankAtms() {
        return bankAtmMapper.toDtoList(bankAtmRepository.findAll());
    }

    /**
     * Чтение всех банкоматов определенного банка.
     *
     * @param bankId id банка, для которого нужно получить банкоматы.
     * @return Список банкоматов, принадлежащих указанному банку.
     */
    public List<BankAtmDto> getAllBankAtmsByBankId(int bankId) {
        return bankAtmMapper.toDtoList(bankAtmRepository.findAllByBankId(bankId));
    }

    /**
     * Обновление информации о банкомате по его идентификатору.
     *
     * @param id   Идентификатор банкомата.
     * @param name Новое название банкомата.
     */
    public BankAtmDto updateBankAtm(int id, String name) {
        BankAtm bankAtm = getBankAtmById(id);
        bankAtm.setName(name);
        return bankAtmMapper.toDto(bankAtmRepository.save(bankAtm));
    }

    /**
     * Удаление банкомата по его идентификатору.
     *
     * @param id Идентификатор банкомата.
     */
    public void deleteBankAtm(int id) {
        bankAtmRepository.deleteById(id);
    }
}
