package ru.siarheyeu.springcourse.FirstSecurityApp.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.siarheyeu.springcourse.FirstSecurityApp.models.Person;
import ru.siarheyeu.springcourse.FirstSecurityApp.repositories.PeopleRepository;

@Service
public class RegistrationService {


    private final PeopleRepository peopleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(PeopleRepository peopleRepository){
        this.peopleRepository = peopleRepository;
    }

    @Transactional
    public void register(Person person){
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        person.setRole("ROLE_USER");
        peopleRepository.save(person);

}
}
