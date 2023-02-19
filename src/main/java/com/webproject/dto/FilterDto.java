package com.webproject.dto;

import com.webproject.model.Theme;
import lombok.Data;

import java.time.LocalDate;

@Data
public class FilterDto {
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private Theme.ThemeEnum themeEnum;
    private String author;
}
