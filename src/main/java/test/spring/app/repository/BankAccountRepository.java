package test.spring.app.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import test.spring.app.entity.BankAccount;
import test.spring.app.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BankAccountRepository extends JpaRepository<BankAccount, UUID> {


}
