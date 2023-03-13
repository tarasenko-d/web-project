package com.webproject.service;

import com.webproject.dao.ThemeRepo;
import com.webproject.model.Theme;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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

    public Theme createTheme(Theme.ThemeEnum themeEnum) {
        Theme theme = new Theme().setName(themeEnum);

        return themeRepo.save(theme);
    }
}
