package test.spring.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import test.spring.app.entity.BankAccount;
import test.spring.app.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User>findUserByBankAccount(BankAccount bankAccount);
    Optional<User>findUserByUsername(String username);
}
