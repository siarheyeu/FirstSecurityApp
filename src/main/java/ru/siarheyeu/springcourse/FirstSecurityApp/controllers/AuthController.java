package ru.siarheyeu.springcourse.FirstSecurityApp.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.siarheyeu.springcourse.FirstSecurityApp.models.Person;
import ru.siarheyeu.springcourse.FirstSecurityApp.security.JWTUtil;
import ru.siarheyeu.springcourse.FirstSecurityApp.services.RegistrationService;
import ru.siarheyeu.springcourse.FirstSecurityApp.util.PersonValidator;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final RegistrationService registrationService;
    private final PersonValidator personValidator;
    private final JWTUtil jwtUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public AuthController(RegistrationService registrartionService, PersonValidator personValidator, JWTUtil jwtUtil, ModelMapper modelMapper) {
        this.registrationService = registrartionService;
        this.personValidator = personValidator;
        this.jwtUtil = jwtUtil;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/login")
    public String loginPage(){
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("person") Person person){
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
        personValidator.validate(person, bindingResult);

        if(bindingResult.hasErrors())
            return "/auth/registration";

        registrationService.register(person);

        return "redirect:/auth/login";
    }
}
