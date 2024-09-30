package tech.reliab.course.frolovays.bank.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.reliab.course.frolovays.bank.entity.BankOfficeStatus;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankOfficeDto {

    private String name;
    private String address;
    private BankOfficeStatus status;
    private boolean canPlaceAtm;
    private boolean canIssueLoan;
    private boolean cashWithdrawal;
    private boolean cashDeposit;
    private double officeMoney;
    private double rentCost;

    private List<String> atmNames;
    private String bankName;
    private List<String> employeeFullNames;
}
