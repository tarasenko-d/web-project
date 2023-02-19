package com.webproject.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Arrays;

@Data
@Table(name = "themes")
@Entity
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Theme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ThemeEnum name;

    @RequiredArgsConstructor
    public enum ThemeEnum {
        ROMAN("roman"),
        DETECTIVE("detective");

        @Getter
        private final String value;

        public static ThemeEnum from(String name) {
            return Arrays.stream(ThemeEnum.values())
                    .filter(el -> el.getValue().equals(name))
                    .findFirst()
                    .orElseThrow(() -> new EnumConstantNotPresentException(ThemeEnum.class, name));
        }
    }
}
