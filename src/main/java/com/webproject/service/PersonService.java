package com.webproject.service;

import com.webproject.dao.PersonRepo;
import com.webproject.model.Person;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepo personRepo;

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
}
