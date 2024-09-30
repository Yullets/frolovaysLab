package tech.reliab.course.frolovays.bank.web.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.reliab.course.frolovays.bank.model.PaymentAccountRequest;
import tech.reliab.course.frolovays.bank.service.PaymentAccountService;
import tech.reliab.course.frolovays.bank.web.controller.PaymentAccountController;
import tech.reliab.course.frolovays.bank.web.dto.PaymentAccountDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payment-accounts")
public class PaymentAccountImpl implements PaymentAccountController {

    private final PaymentAccountService paymentAccountService;

    @Override
    @PostMapping
    public ResponseEntity<PaymentAccountDto> createPaymentAccount(PaymentAccountRequest paymentAccountRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentAccountService.createPaymentAccount(paymentAccountRequest));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaymentAccount(int id) {
        paymentAccountService.deletePaymentAccount(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<PaymentAccountDto> updatePaymentAccount(int id, int bankId) {
        return ResponseEntity.ok(paymentAccountService.updatePaymentAccount(id, bankId));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<PaymentAccountDto> getBankByPaymentAccount(int id) {
        return ResponseEntity.ok(paymentAccountService.getPaymentAccountDtoById(id));
    }

    @Override
    @GetMapping
    public ResponseEntity<List<PaymentAccountDto>> getAllPaymentAccounts() {
        return ResponseEntity.ok(paymentAccountService.getAllPaymentAccounts());
    }
}
