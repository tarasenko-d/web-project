package com.webproject.controller;

import com.webproject.dto.IntegrationMessage;
import com.webproject.dto.request.CreateAuthorRequest;
import com.webproject.model.Author;
import com.webproject.model.Person;
import com.webproject.service.AuthorService;
import com.webproject.service.validate.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SuppressWarnings("rawtypes")
@RequiredArgsConstructor
public class CreateAuthorController {
    private final AuthorService authorService;
    private final ValidationService<CreateAuthorRequest> validationService;

    @PostMapping(path = "/author/create")
    public IntegrationMessage createAuthor(@RequestBody IntegrationMessage<CreateAuthorRequest> request, @AuthenticationPrincipal Person person) {
        try {
            validationService.validate(request, person);

            Author newAuthor = authorService.createAuthor(request.getPayload().getAuthorInfo());

            return IntegrationMessage.successResponse(newAuthor, request);
        } catch (Exception exception) {
            return IntegrationMessage.exceptionResponse(exception.getMessage(), request);
        }
    }
}
