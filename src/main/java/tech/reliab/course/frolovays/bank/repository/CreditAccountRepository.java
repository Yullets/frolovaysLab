package tech.reliab.course.frolovays.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.reliab.course.frolovays.bank.entity.CreditAccount;

import java.util.Optional;

public interface CreditAccountRepository extends JpaRepository<CreditAccount, Integer> {

    Optional<CreditAccount> findById(int id);

    void deleteById(int id);
}
