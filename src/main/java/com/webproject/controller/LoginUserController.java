package com.webproject.controller;

import com.webproject.config.security.JwtProvider;
import com.webproject.dto.IntegrationMessage;
import com.webproject.dto.request.LoginRequest;
import com.webproject.model.Person;
import com.webproject.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static java.util.Objects.isNull;

@RestController
@SuppressWarnings("rawtypes")
@RequiredArgsConstructor
public class LoginUserController {
    private final PersonService personService;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    @PostMapping(path = "/users/login")
    public IntegrationMessage login(@RequestBody IntegrationMessage<LoginRequest> request) {
        try {
            LoginRequest.AuthInfo payload = Optional.ofNullable(request)
                    .map(IntegrationMessage::getPayload)
                    .map(LoginRequest::getAuthInfo)
                    .orElse(null);

            if (isNull(payload)) {
                return IntegrationMessage.errorResponse("Payload is not present", request);
            }

            Person person = personService.getByLogin(payload.getUsername());

            if (passwordEncoder.matches(person.getPassword(), payload.getPassword())) {
                return IntegrationMessage.errorResponse("Password or login not correctly", request);
            }

            String token = jwtProvider.generateToken(person.getLogin(), person.getRoles());

            return IntegrationMessage.successResponse(token, request);
        } catch (Exception exception) {
            return IntegrationMessage.exceptionResponse(exception.getMessage(), request);
        }
    }
}
