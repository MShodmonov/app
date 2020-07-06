package test.spring.app.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import test.spring.app.entity.BankAccount;
import test.spring.app.entity.BankCredits;
import test.spring.app.entity.Role;
import test.spring.app.entity.User;
import test.spring.app.entity.enums.RoleEnumeration;
import test.spring.app.repository.BankAccountRepository;
import test.spring.app.repository.BankCreditRepository;
import test.spring.app.repository.RoleRepository;
import test.spring.app.repository.UserRepository;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BankCreditRepository bankCreditRepository;

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        Role admin = new Role(RoleEnumeration.ROLE_ADMIN);
        Role user = new Role(RoleEnumeration.ROLE_USER);
        Role moderator = new Role(RoleEnumeration.ROLE_MODERATOR);

        roleRepository.save(admin);
        roleRepository.save(user);
        roleRepository.save(moderator);


        java.util.Date dateNow = new java.util.Date();
        Date date = new Date(dateNow.getTime());

        User user1 =new User("musobek",passwordEncoder.encode("root123"),"Musobek","Shodmonov",date,roleRepository.findAll());
        userRepository.save(user1);

        BankAccount bankAccount = new BankAccount(12345L,Long.parseLong("7832782"),null,user1);
        BankAccount bankAccount1 = bankAccountRepository.save(bankAccount);
        BankCredits bankCredit= new BankCredits(user1,"one month",bankAccount1);
        BankCredits bankCredit1 = new BankCredits(user1,"ten month",bankAccount1);
        BankCredits credits = bankCreditRepository.save(bankCredit);
        BankCredits credits1 = bankCreditRepository.save(bankCredit1);


    }
}
