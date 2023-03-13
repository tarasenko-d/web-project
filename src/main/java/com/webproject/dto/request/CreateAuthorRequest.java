package com.webproject.dto.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateAuthorRequest {
    private AuthorInfo authorInfo;

    @Data
    public static class AuthorInfo {
        private String name;
        private LocalDate birthDate;
    }
}
