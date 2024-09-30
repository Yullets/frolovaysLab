package tech.reliab.course.frolovays.bank.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import tech.reliab.course.frolovays.bank.entity.Bank;
import tech.reliab.course.frolovays.bank.entity.CreditAccount;
import tech.reliab.course.frolovays.bank.entity.PaymentAccount;
import tech.reliab.course.frolovays.bank.entity.User;
import tech.reliab.course.frolovays.bank.web.dto.UserDto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "banks", target = "bankNames", qualifiedByName = "mapBankNames")
    @Mapping(source = "creditAccounts", target = "creditLoanAmounts", qualifiedByName = "mapCreditLoanAmounts")
    @Mapping(source = "paymentAccounts", target = "paymentBalances", qualifiedByName = "mapPaymentBalances")
    UserDto toDto(User user);

    List<UserDto> toDtoList(List<User> users);

    @Named("mapBankNames")
    default List<String> mapBankNames(List<Bank> banks) {
        if (banks == null) {
            return Collections.emptyList();
        }
        return banks.stream()
                .map(Bank::getName)
                .collect(Collectors.toList());
    }

    @Named("mapCreditLoanAmounts")
    default List<Double> mapCreditLoanAmounts(List<CreditAccount> creditAccounts) {
        if (creditAccounts == null) {
            return Collections.emptyList();
        }
        return creditAccounts.stream()
                .map(CreditAccount::getLoanAmount)
                .collect(Collectors.toList());
    }

    @Named("mapPaymentBalances")
    default List<Double> mapPaymentBalances(List<PaymentAccount> paymentAccounts) {
        if (paymentAccounts == null) {
            return Collections.emptyList();
        }
        return paymentAccounts.stream()
                .map(PaymentAccount::getBalance)
                .collect(Collectors.toList());
    }
}
