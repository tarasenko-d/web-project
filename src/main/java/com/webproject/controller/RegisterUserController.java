package com.webproject.controller;

import com.webproject.config.security.JwtProvider;
import com.webproject.dto.IntegrationMessage;
import com.webproject.dto.request.RegisterRequest;
import com.webproject.model.Person;
import com.webproject.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static java.util.Objects.isNull;

@RestController
@SuppressWarnings("rawtypes")
@RequiredArgsConstructor
public class RegisterUserController {
    private final PersonService personService;
    private final JwtProvider jwtProvider;

    @PostMapping(path = "/users/register")
    public IntegrationMessage register(@RequestBody IntegrationMessage<RegisterRequest> request) {
        try {
            RegisterRequest.AuthInfo payload = Optional.ofNullable(request)
                    .map(IntegrationMessage::getPayload)
                    .map(RegisterRequest::getAuthInfo)
                    .orElse(null);

            if (isNull(payload)) {
                return IntegrationMessage.errorResponse("Payload is not present", request);
            }

            Person person = personService.register(payload);
            String token = jwtProvider.generateToken(person.getLogin(), person.getRoles());

            return IntegrationMessage.successResponse(token, request);
        } catch (Exception exception) {
            return IntegrationMessage.exceptionResponse(exception.getMessage(), request);
        }
    }
}
