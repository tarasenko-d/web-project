package com.webproject.service;

import com.webproject.WebProjectApplication;
import com.webproject.config.security.JwtProvider;
import com.webproject.model.Person;
import com.webproject.model.Role;
import com.webproject.model.Theme;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class TestService implements CommandLineRunner {

    private final JwtProvider jwtProvider;
    private final TagService tagService;
    private final ThemeService themeService;
    private final AuthorService authorService;

    public static void main(String[] args) {
        SpringApplication.run(WebProjectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Person person = new Person();
        person.setRoles(Set.of(new Role(1L, Role.RoleEnum.USER)));

        UserDetailsServiceImpl.UserDetailsImpl userDetails = new UserDetailsServiceImpl.UserDetailsImpl(person);

        System.out.println(jwtProvider.generateToken("user", userDetails.getAuthorities()));
        tagService.createTag("title");
        themeService.createTheme(Theme.ThemeEnum.ROMAN);
    }
}
