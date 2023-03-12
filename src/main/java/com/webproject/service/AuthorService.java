package com.webproject.service;

import com.webproject.dao.AuthorRepo;
import com.webproject.model.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
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

    public List<Author> getAlphabetically(Long count) {
        Sort sort = Sort.by("name");
        PageRequest pageable = PageRequest.of(0, count.intValue(), sort);

        Page<Author> authors = authorRepo.findAll(pageable);

        return authors.getContent();
    }
}
