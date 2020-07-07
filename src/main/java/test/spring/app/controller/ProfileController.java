package test.spring.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import test.spring.app.entity.User;
import test.spring.app.model.ProfileRequest;
import test.spring.app.repository.BankAccountRepository;
import test.spring.app.repository.BankCreditRepository;
import test.spring.app.repository.UserRepository;
import test.spring.app.security.CurrentUser;
import test.spring.app.services.AuthService;

@Controller
public class ProfileController {
    
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private AuthService authService;
    @Autowired
    private BankCreditRepository bankCreditRepository;

    @GetMapping({"/","/index","/home"})
    public String getHomePage()
    {
        return "/index";
    }
    
    @GetMapping("/profile")

    public String getProfile(@CurrentUser User user, Model model)
    {
        ProfileRequest profileRequest = authService.getProfileRequest(user);
        model.addAttribute("profileRequest",profileRequest);
        return "cabinet/profile";
    }
}
