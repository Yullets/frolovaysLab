package tech.reliab.course.frolovays.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.reliab.course.frolovays.bank.entity.BankOffice;

import java.util.Optional;

public interface BankOfficeRepository extends JpaRepository<BankOffice, Integer> {

    Optional<BankOffice> findById(int id);

    void deleteById(int id);
}
