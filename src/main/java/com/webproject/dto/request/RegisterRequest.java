package com.webproject.dto.request;

import lombok.Data;

@Data
public class RegisterRequest {
    private AuthInfo authInfo;

    @Data
    public static class AuthInfo {
        private String username;
        private String password;
    }
}
