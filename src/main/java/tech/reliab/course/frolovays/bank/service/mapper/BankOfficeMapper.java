package tech.reliab.course.frolovays.bank.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import tech.reliab.course.frolovays.bank.entity.BankAtm;
import tech.reliab.course.frolovays.bank.entity.BankOffice;
import tech.reliab.course.frolovays.bank.entity.Employee;
import tech.reliab.course.frolovays.bank.web.dto.BankOfficeDto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface BankOfficeMapper {

    @Mapping(source = "atms", target = "atmNames", qualifiedByName = "mapAtmNames")
    @Mapping(source = "bank.name", target = "bankName")
    @Mapping(source = "employees", target = "employeeFullNames", qualifiedByName = "mapEmployeeFullNames")
    BankOfficeDto toDto(BankOffice bankOffice);

    List<BankOfficeDto> toDtoList(List<BankOffice> bankOffices);

    @Named("mapAtmNames")
    default List<String> mapAtmNames(List<BankAtm> atms) {
        if (atms == null) {
            return Collections.emptyList();
        }
        return atms.stream()
                .map(BankAtm::getName)
                .collect(Collectors.toList());
    }

    @Named("mapEmployeeFullNames")
    default List<String> mapEmployeeFullNames(List<Employee> employees) {
        if (employees == null) {
            return Collections.emptyList();
        }
        return employees.stream()
                .map(Employee::getFullName)
                .collect(Collectors.toList());
    }
}
