package ru.siarheyeu.springcourse.FirstSecurityApp.controllers;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.siarheyeu.springcourse.FirstSecurityApp.security.PersonDetails;

import java.sql.SQLOutput;

@Controller
public class HelloController {
    @GetMapping("/hello")
    public String sayHello(){
        return "hello";

        @GetMapping("/showUserInfo")
            public String showUserInfo (){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            PersonDetails personDetails (PersonDetails)authentication.getPrincipal();
            System.out.println(personDetails.getPerson());

            return "hello";
        }

    }


}
