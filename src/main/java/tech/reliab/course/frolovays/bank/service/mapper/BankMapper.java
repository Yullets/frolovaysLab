package tech.reliab.course.frolovays.bank.service.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import tech.reliab.course.frolovays.bank.entity.*;
import tech.reliab.course.frolovays.bank.web.dto.BankDto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface BankMapper {

    @Mapping(source = "offices", target = "officeNames", qualifiedByName = "mapOfficeNames")
    @Mapping(source = "atms", target = "atmNames", qualifiedByName = "mapAtmNames")
    @Mapping(source = "employees", target = "employeeNames", qualifiedByName = "mapEmployeeNames")
    @Mapping(source = "creditAccounts", target = "creditAccountLoanAmounts", qualifiedByName = "mapCreditAccountLoanAmount")
    @Mapping(source = "paymentAccounts", target = "paymentAccountBalances", qualifiedByName = "mapPaymentAccountBalance")
    BankDto toDto(Bank bank);

    List<BankDto> toDtoList(List<Bank> banks);

    @Named("mapOfficeNames")
    default List<String> mapOfficeNames(List<BankOffice> offices) {
        if (offices == null) {
            return Collections.emptyList();
        }
        return offices.stream()
                .map(BankOffice::getName)
                .collect(Collectors.toList());
    }

    @Named("mapAtmNames")
    default List<String> mapAtmNames(List<BankAtm> atms) {
        if (atms == null) {
            return Collections.emptyList();
        }
        return atms.stream()
                .map(BankAtm::getName)
                .collect(Collectors.toList());
    }

    @Named("mapEmployeeNames")
    default List<String> mapEmployeeNames(List<Employee> employees) {
        if (employees == null) {
            return Collections.emptyList();
        }
        return employees.stream()
                .map(Employee::getFullName)
                .collect(Collectors.toList());
    }

    @Named("mapCreditAccountLoanAmount")
    default List<Double> mapCreditAccountLoanAmount(List<CreditAccount> creditAccounts) {
        if (creditAccounts == null) {
            return Collections.emptyList();
        }
        return creditAccounts.stream()
                .map(CreditAccount::getLoanAmount)
                .collect(Collectors.toList());
    }

    @Named("mapPaymentAccountBalance")
    default List<Double> mapPaymentAccountBalance(List<PaymentAccount> paymentAccounts) {
        if (paymentAccounts == null) {
            return Collections.emptyList();
        }
        return paymentAccounts.stream()
                .map(PaymentAccount::getBalance)
                .collect(Collectors.toList());
    }
}
