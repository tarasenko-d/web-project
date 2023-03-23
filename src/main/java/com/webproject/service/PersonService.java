package com.webproject.service;

import com.webproject.dao.PersonRepo;
import com.webproject.dto.request.RegisterRequest;
import com.webproject.model.Person;
import com.webproject.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepo personRepo;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public Person getById(Long id) {
        Optional<Person> modelOptional = personRepo.findById(id);

        if (modelOptional.isEmpty()) {
            String message = String.format("User with id %s not found", id);
            throw new EntityNotFoundException(message);
        }

        return modelOptional.get();
    }

    public Person getByLogin(String login) {
        Optional<Person> modelOptional = personRepo.findByLogin(login);

        if (modelOptional.isEmpty()) {
            String message = String.format("User with login %s not found", login);
            throw new EntityNotFoundException(message);
        }

        return modelOptional.get();
    }

    public Person register(RegisterRequest.AuthInfo authInfo) {
        Role role = roleService.getRole(Role.RoleEnum.USER);

        Person person = new Person()
                .setRoles(Set.of(role))
                .setLogin(authInfo.getUsername())
                .setPassword(passwordEncoder.encode(authInfo.getPassword()));

        return personRepo.save(person);
    }
}
