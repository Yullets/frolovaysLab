package tech.reliab.course.frolovays.bank.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tech.reliab.course.frolovays.bank.entity.CreditAccount;
import tech.reliab.course.frolovays.bank.web.dto.CreditAccountDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CreditAccountMapper {

    @Mapping(source = "user.fullName", target = "userName")
    @Mapping(source = "bank.name", target = "bankName")
    @Mapping(source = "employee.fullName", target = "employeeFullName")
    @Mapping(source = "paymentAccount.balance", target = "paymentAccountBalance")
    CreditAccountDto toDto(CreditAccount creditAccount);

    List<CreditAccountDto> toDtoList(List<CreditAccount> creditAccounts);
}
