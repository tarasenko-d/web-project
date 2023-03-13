package com.webproject.controller;

import com.webproject.dto.IntegrationMessage;
import com.webproject.dto.request.GetNAuthorsAlphabetRequest;
import com.webproject.model.Author;
import com.webproject.model.Book;
import com.webproject.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.isNull;

@RestController
@SuppressWarnings("rawtypes")
@RequiredArgsConstructor
public class GetNAuthorsAlphabetController {
    private final AuthorService authorService;

    @PostMapping(path = "/authors/getAlphabetically")
    public IntegrationMessage getAlphabetically(@RequestBody IntegrationMessage<GetNAuthorsAlphabetRequest> request) {
        try {
            Long count = Optional.ofNullable(request)
                    .map(IntegrationMessage::getPayload)
                    .map(GetNAuthorsAlphabetRequest::getAuthorsQuantity)
                    .orElse(null);

            if (isNull(count) || count.intValue() == 0) {
                return IntegrationMessage.errorResponse("Payload is not present", request);
            }

            List<Author> books = authorService.getAlphabetically(count);

            return IntegrationMessage.successResponse(books, request);
        } catch (Exception exception) {
            return IntegrationMessage.exceptionResponse(exception.getMessage(), request);
        }
    }
}
