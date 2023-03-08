package ru.siarheyeu.springcourse.FirstSecurityApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.siarheyeu.springcourse.FirstSecurityApp.models.Person;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/login")
    public String loginPage(){
        return "auth/login";
    }

    public String registrationPage(@ModelAttribute("person") Person person){
        return "auth/registration";
    }
}
