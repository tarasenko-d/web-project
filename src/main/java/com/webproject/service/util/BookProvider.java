package com.webproject.service.util;

import com.webproject.dto.request.CreateBookRequest;
import com.webproject.model.Author;
import com.webproject.model.Book;
import com.webproject.model.Tag;
import com.webproject.model.Theme;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.util.Set;

@UtilityClass
public class BookProvider {

    public static Book generateModel(CreateBookRequest.BookInfo bookInfo, Author author, Set<Tag> tags, Set<Theme> themes) {
        Book book = new Book();

        book.setCounter(0L);
        book.setAddDate(LocalDate.now());

        book.setTitle(bookInfo.getTitle());
        book.setCreatedDate(bookInfo.getCreatedDate());
        book.setText(bookInfo.getText());

        book.setAuthor(author);
        book.setThemes(themes);
        book.setTags(tags);

        return book;
    }
}
