package com.webproject.dao;

import com.webproject.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<Book, Long> {

    Page<Book> findAll(Pageable pageable);

    Book findBookByTitleAndAuthorName(String title, String author);
}
