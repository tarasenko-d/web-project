package com.webproject.dao;

import com.webproject.model.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface ThemeRepo extends JpaRepository<Theme, Long> {

    List<Theme> findAllByNameIn(Set<Theme.ThemeEnum> names);
}