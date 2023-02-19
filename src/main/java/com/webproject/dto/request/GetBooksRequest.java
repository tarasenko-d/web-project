package com.webproject.dto.request;

import lombok.Data;

@Data
public class GetBooksRequest {
    private PaginationInfo paginationInfo;

    @Data
    public static class PaginationInfo {
        private int page;
        private int size;
    }
}
