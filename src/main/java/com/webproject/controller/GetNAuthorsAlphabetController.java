package com.webproject.controller;

import com.webproject.dto.IntegrationMessage;
import com.webproject.dto.request.GetNAuthorsAlphabetRequest;
import com.webproject.model.Book;
import com.webproject.service.BookService;
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
    private final BookService bookService;

    @PostMapping(path = "/book/getAlphabetically")
    public IntegrationMessage getBooks(@RequestBody IntegrationMessage<GetNAuthorsAlphabetRequest> request) {
        try {
            Long payload = Optional.ofNullable(request)
                    .map(IntegrationMessage::getPayload)
                    .map(GetNAuthorsAlphabetRequest::getAuthorsQuantity)
                    .orElse(null);

            if (isNull(payload)) {
                return IntegrationMessage.errorResponse("Payload is not present");
            }

            List<Book> books = bookService.getNBooksAlphabetically(payload);

            return IntegrationMessage.successResponse(books);
        } catch (Exception exception) {
            return IntegrationMessage.exceptionResponse(exception.getMessage());
        }
    }
}
