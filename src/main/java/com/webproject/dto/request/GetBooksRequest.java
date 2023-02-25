package com.webproject.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.Set;

@Data
public class GetBooksRequest {
    private Filter filter;
    private PaginationInfo paginationInfo;

    @Data
    @Accessors(chain = true)
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Filter {
        private String author;
        private LocalDate createdFrom;
        private LocalDate createdTo;
        private LocalDate addDateFrom;
        private LocalDate addDateTo;
        private String title;
        private Set<String> theme;
        private Set<String> tags;
    }

    @Data
    @Accessors(chain = true)
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PaginationInfo {
        private int page;
        private int size;
    }

    public GetBooksRequest() {
        this.paginationInfo = new PaginationInfo()
                .setPage(0)
                .setSize(1);
    }
}
