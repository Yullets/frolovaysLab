package tech.reliab.course.frolovays.bank.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankDto {

    private String name;

    private int rating;

    private double totalMoney;

    private double interestRate;

    private List<String> officeNames;

    private List<String> atmNames;

    private List<String> employeeNames;

    private List<Double> creditAccountLoanAmounts;

    private List<Double> paymentAccountBalances;
}
