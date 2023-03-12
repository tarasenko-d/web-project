package com.webproject.service;

import com.webproject.dao.BookRepo;
import com.webproject.dto.request.CreateBookRequest;
import com.webproject.dto.request.GetBooksRequest;
import com.webproject.model.Author;
import com.webproject.model.Book;
import com.webproject.model.Tag;
import com.webproject.model.Theme;
import com.webproject.service.util.BookProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepo bookRepo;
    private final AuthorService authorService;
    private final TagService tagService;
    private final ThemeService themeService;

    public List<Book> getBooks(GetBooksRequest.PaginationInfo paginationInfo) {
        PageRequest pageable = PageRequest.of(paginationInfo.getPage(), paginationInfo.getSize());

        Page<Book> all = bookRepo.findAll(pageable);
        return all.getContent();
    }

    public Book getById(Long id) {
        Optional<Book> modelOptional = bookRepo.findById(id);

        if (modelOptional.isEmpty()) {
            String message = String.format("Book with id %s not found", id);
            throw new EntityNotFoundException(message);
        }

        return modelOptional.get();
    }

    @Transactional
    public Book createNewBook(CreateBookRequest.BookInfo bookInfo) {
        Author author = authorService.getByName(bookInfo.getAuthor());
        Set<Tag> tags = tagService.getByNames(bookInfo.getTags());
        Set<Theme> themes = themeService.getByNames(bookInfo.getThemes());

        Book book = BookProvider.generateModel(bookInfo, author, tags, themes);

        return bookRepo.save(book);
    }
}
