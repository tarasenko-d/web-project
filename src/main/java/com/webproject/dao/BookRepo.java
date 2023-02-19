package com.webproject.dao;

import com.webproject.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface BookRepo extends CrudRepository<Book, Long> {

    Page<Book> findAll(Pageable pageable);
}
