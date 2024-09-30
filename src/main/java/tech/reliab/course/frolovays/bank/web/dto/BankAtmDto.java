package tech.reliab.course.frolovays.bank.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.reliab.course.frolovays.bank.entity.BankAtmStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankAtmDto {

    private int id;
    private String name;
    private String address;
    private boolean cashWithdrawal;
    private boolean cashDeposit;
    private double atmMoney;
    private double maintenanceCost;
    private BankAtmStatus status;
    private String locationAddress;
    private String employeeFullName;
    private String bankName;
}
