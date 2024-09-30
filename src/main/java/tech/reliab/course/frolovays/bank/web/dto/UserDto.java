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
public class UserDto {

    private String fullName;
    private LocalDate birthDate;
    private String job;
    private double monthlyIncome;
    private int creditRating;

    private List<String> bankNames;
    private List<Double> creditLoanAmounts;
    private List<Double> paymentBalances;
}
