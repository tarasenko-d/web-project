package com.webproject.dto.response;

import com.webproject.model.Author;
import lombok.Data;

import java.util.List;

@Data
public class GetNAuthorsAlphabetResponse {
    private List<Author> authors;
}
