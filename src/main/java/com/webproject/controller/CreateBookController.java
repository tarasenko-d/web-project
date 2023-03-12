package com.webproject.controller;

import com.webproject.dto.IntegrationMessage;
import com.webproject.dto.request.CreateBookRequest;
import com.webproject.model.Book;
import com.webproject.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static java.util.Objects.isNull;

@RestController
@SuppressWarnings("rawtypes")
@RequiredArgsConstructor
public class CreateBookController {

    private final BookService bookService;

    @PostMapping(path = "/books/create")
    public IntegrationMessage createBook(@RequestBody IntegrationMessage<CreateBookRequest> request) {
        try {
            CreateBookRequest.BookInfo payload = Optional.ofNullable(request)
                    .map(IntegrationMessage::getPayload)
                    .map(CreateBookRequest::getBookInfo)
                    .orElse(null);

            if (isNull(payload)) {
                return IntegrationMessage.errorResponse("Payload is not present");
            }

            Book newBook = bookService.createNewBook(payload);

            return IntegrationMessage.successResponse(newBook);
        } catch (Exception exception) {
            return IntegrationMessage.exceptionResponse(exception.getMessage());
        }
    }
}
