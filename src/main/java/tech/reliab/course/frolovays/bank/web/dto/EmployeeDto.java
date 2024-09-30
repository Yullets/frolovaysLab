package tech.reliab.course.frolovays.bank.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

    private String fullName;

    private LocalDate birthDate;

    private String position;

    private String bankName;

    private boolean remoteWork;

    private String bankOfficeName;

    private boolean canIssueLoans;

    private double salary;

    private List<String> bankAtmNames;
}
