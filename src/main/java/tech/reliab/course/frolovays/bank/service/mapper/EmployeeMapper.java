package tech.reliab.course.frolovays.bank.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import tech.reliab.course.frolovays.bank.entity.BankAtm;
import tech.reliab.course.frolovays.bank.entity.Employee;
import tech.reliab.course.frolovays.bank.web.dto.EmployeeDto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mapping(source = "bank.name", target = "bankName")
    @Mapping(source = "bankOffice.name", target = "bankOfficeName")
    @Mapping(source = "bankAtm", target = "bankAtmNames", qualifiedByName = "mapAtmNames")
    EmployeeDto toDto(Employee employee);

    List<EmployeeDto> toDtoList(List<Employee> employees);

    @Named("mapAtmNames")
    default List<String> mapAtmNames(List<BankAtm> atms) {
        if (atms == null) {
            return Collections.emptyList();
        }
        return atms.stream()
                .map(BankAtm::getName)
                .collect(Collectors.toList());
    }
}
