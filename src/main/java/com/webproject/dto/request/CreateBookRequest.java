package com.webproject.dto.request;

import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class CreateBookRequest {
    private BookInfo bookInfo;

    @Data
    public static class BookInfo {
        private String author;
        private LocalDate createdDate;
        private String title;
        private String text;
        private Set<String> themes;
        private Set<String> tags;
    }
}
