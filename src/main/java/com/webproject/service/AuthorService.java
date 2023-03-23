package com.webproject.service;

import com.webproject.dao.AuthorRepo;
import com.webproject.dto.request.CreateAuthorRequest;
import com.webproject.model.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepo authorRepo;

    public Author createAuthor(CreateAuthorRequest.AuthorInfo info) {
        Author author = new Author();
        author
                .setBirthDate(info.getBirthDate())
                .setName(info.getName());

        return authorRepo.save(author);
    }

    public Author getByName(String name) {
        return authorRepo.getAuthorByName(name);
    }

    public List<Author> getAlphabetically(Long count) {
        Sort sort = Sort.by("name");
        PageRequest pageable = PageRequest.of(0, count.intValue(), sort);

        Page<Author> authors = authorRepo.findAll(pageable);

        return authors.getContent();
    }
}
