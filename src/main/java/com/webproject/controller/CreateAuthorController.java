package com.webproject.controller;

import com.webproject.dto.IntegrationMessage;
import com.webproject.dto.request.CreateAuthorRequest;
import com.webproject.model.Author;
import com.webproject.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static java.util.Objects.isNull;

@RestController
@SuppressWarnings("rawtypes")
@RequiredArgsConstructor
public class CreateAuthorController {

    private final AuthorService authorService;

    @PostMapping(path = "/author/create")
    public IntegrationMessage createAuthor(@RequestBody IntegrationMessage<CreateAuthorRequest> request) {
        try {
            CreateAuthorRequest.AuthorInfo payload = Optional.ofNullable(request)
                    .map(IntegrationMessage::getPayload)
                    .map(CreateAuthorRequest::getAuthorInfo)
                    .orElse(null);

            if (isNull(payload)) {
                return IntegrationMessage.errorResponse("Payload is not present", request);
            }

            Author newAuthor = authorService.createAuthor(payload);

            return IntegrationMessage.successResponse(newAuthor, request);
        } catch (Exception exception) {
            return IntegrationMessage.exceptionResponse(exception.getMessage(), request);
        }
    }
}
