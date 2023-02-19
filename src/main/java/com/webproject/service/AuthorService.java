package com.webproject.service;

import com.webproject.dao.AuthorRepo;
import com.webproject.model.Author;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepo authorRepo;

    public Author getByName(String name) {
        Optional<Author> modelOptional = authorRepo.getAuthorByName(name);

        if (modelOptional.isEmpty()) {
            String message = String.format("Author with name %s not found", name);
            throw new EntityNotFoundException(message);
        }

        return modelOptional.get();
    }
}
