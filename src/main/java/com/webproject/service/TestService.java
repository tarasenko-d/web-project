package com.webproject.service;

import com.webproject.model.Role;
import com.webproject.model.Theme;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestService implements CommandLineRunner {

    private final TagService tagService;
    private final ThemeService themeService;
    private final RoleService roleService;

    @Override
    public void run(String... args) throws Exception {
        roleService.addRole(Role.RoleEnum.USER);
        roleService.addRole(Role.RoleEnum.ADMIN);
        tagService.createTag("title");
        themeService.createTheme(Theme.ThemeEnum.ROMAN);
    }
}
