package com.webproject.dto.response;

import com.webproject.model.Book;
import lombok.Data;

import java.util.List;

@Data
public class GetBooksResponse {
    private List<Book> books;
}
