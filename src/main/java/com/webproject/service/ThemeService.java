package com.webproject.service;

import com.webproject.dao.ThemeRepo;
import com.webproject.model.Theme;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ThemeService {

    private final ThemeRepo themeRepo;

    public Set<Theme> getByNames(Set<String> names) {
        Set<Theme.ThemeEnum> themeSet = names
                .stream()
                .map(Theme.ThemeEnum::from)
                .collect(Collectors.toSet());

        List<Theme> tags = themeRepo.findAllByNameIn(themeSet);

        if (tags.isEmpty()) {
            throw new EntityNotFoundException("Tags not found");
        }

        return new HashSet<>(tags);
    }
}
