package test.spring.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import test.spring.app.entity.BankAccount;
import test.spring.app.entity.BankCredits;
import test.spring.app.entity.User;
import test.spring.app.entity.enums.RoleEnumeration;
import test.spring.app.model.ProfileRequest;
import test.spring.app.model.RegisterRequest;
import test.spring.app.repository.BankAccountRepository;
import test.spring.app.repository.BankCreditRepository;
import test.spring.app.repository.RoleRepository;
import test.spring.app.repository.UserRepository;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthService implements UserDetailsService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BankAccountRepository bankAccountRepository;
    private BankCreditRepository bankCreditRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(UserRepository userRepository, RoleRepository roleRepository, BankAccountRepository bankAccountRepository, BankCreditRepository bankCreditRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.bankCreditRepository = bankCreditRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        return userRepository.findUserByUsername(s).orElseThrow(
                () -> new UsernameNotFoundException("The username is not registered"));

    }

    public boolean isAuthenticated() {
        return !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken);
    }

    @Transactional
    public User registerUser(RegisterRequest registerRequest) {
        Optional<User> userByUsername = userRepository.findUserByUsername(registerRequest.getUsername());
        if (userByUsername.isPresent())
            return null;
        else {
            try {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date parse = format.parse(registerRequest.getBirthDate());
                java.sql.Date finalDate = new java.sql.Date(parse.getTime());


                User save =new User(registerRequest.getUsername(),
                        passwordEncoder.encode(registerRequest.getPassword()),
                        registerRequest.getName(), registerRequest.getSurname(),
                        finalDate, roleRepository.findAllByName(RoleEnumeration.ROLE_USER));
                User save1 = userRepository.save(save);
                BankAccount bankAccount = bankAccountRepository.save(new BankAccount(registerRequest.getBankAccount(),0L,null,save));
                return save1;



            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    @Transactional
    public ProfileRequest getProfileRequest(User user) {
        BankAccount bankAccount = user.getBankAccount();
        List<String> creditList = null;
        if (true)
            return new ProfileRequest(user.getPassword(), user.getUsername(),
                    user.getName(), user.getSurname(), bankAccount.getNumber(), user.getBirthday().toString(),
                    bankAccount.getBalance(),creditList);
        List<BankCredits> bankCredits = bankAccount.getBankCredits();
        if (!bankCredits.isEmpty()) {
            creditList = new LinkedList<>();
            for (BankCredits credit : bankCredits) {
                creditList.add(credit.getCreditName());
            }
        }
        return new ProfileRequest(user.getPassword(), user.getUsername(),
                user.getName(), user.getSurname(), bankAccount.getNumber(), user.getBirthday().toString(),
                bankAccount.getBalance(), creditList);


    }


}

