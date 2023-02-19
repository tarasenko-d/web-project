package com.webproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.Set;

@Data
@Table(name = "books")
@Entity
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Author author;

    private LocalDate createdDate;
    private LocalDate addDate;
    private String title;
    private String text;

    private Long counter;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Theme> themes;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Tag> tags;
}
