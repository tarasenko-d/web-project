package com.webproject.dao;

import com.webproject.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepo extends JpaRepository<Author, Long> {

    Author getAuthorByName(String name);
}
