package com.example.onlinestorenew.controllers;

import com.example.onlinestorenew.models.UserEntity;
import com.example.onlinestorenew.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class AuthController {
    @GetMapping(value = "/login")
    public String Login() {
        return "/login";
    }

    @GetMapping(value = "/signup")
    public String SignupView() {
        return "/signup";
    }
    @PostMapping(value = "/signup")
    public ModelAndView SignUp(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "city") String city,
            @RequestParam(name = "country") String country,
            @RequestParam(name = "phone") String phone,
            @RequestParam(name = "email") String email,
            @RequestParam(name = "password") String password,
            @RequestParam(name = "password2") String password2
            ) {
        if(!password.equals(password2)) {
            return new ModelAndView("/signup", "message", "Passwords doesn't match");
        }

        UserService userService = new UserService();
        if(userService.findByEmail(email) != null) {
            return new ModelAndView("/signup", "message", "User already exists");
        }

        UserEntity newUser = new UserEntity();
        newUser.setName(name);
        newUser.setCity(city);
        newUser.setCountry(country);
        newUser.setPhone(phone);
        newUser.setEmail(email);
        newUser.setPassword(password);

        if(!userService.createUser(newUser)) {
            return new ModelAndView("/signup", "message", "Some error");
        }
        return new ModelAndView("/index");
    }
}
