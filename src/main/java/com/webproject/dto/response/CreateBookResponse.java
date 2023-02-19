package com.webproject.dto.response;

import com.webproject.model.Book;
import lombok.Data;

@Data
public class CreateBookResponse {
    private Book book;
}
