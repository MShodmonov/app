package test.spring.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import test.spring.app.model.RegisterRequest;
import test.spring.app.services.AuthService;

import javax.validation.Valid;

@Controller
public class AuthController {


    private  AuthService authService;


    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }




    @GetMapping("/login")
    public String getLoginPage(Model model)
    {
        if (authService.isAuthenticated())
            return "redirect:/index";
        else{
            return "/login";
        }
    }

    @GetMapping("/register")
    public String getSignInPage(Model model)
    {
        if (!authService.isAuthenticated())
        {
            model.addAttribute("registerRequest",new RegisterRequest());
            model.addAttribute("isRegister",false);
            return "register";
        }
        else return "/index";

    }
    @PostMapping("/register")
    public String postLoginPage(@Valid RegisterRequest registerRequest, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("registerRequest", registerRequest);
            model.addAttribute("isRegister",false);
            return "/register";
        }
        else {

            if (authService.registerUser(registerRequest) != null)
            {
                return "/login";
            }
            else {
                model.addAttribute("registerRequest", registerRequest);
                model.addAttribute("isRegister",false);
                return "/register";
            }
        }
    }

}
