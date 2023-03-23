package com.webproject.controller;

import com.webproject.dto.IntegrationMessage;
import com.webproject.dto.request.CreateBookRequest;
import com.webproject.model.Book;
import com.webproject.model.Person;
import com.webproject.service.BookService;
import com.webproject.service.validate.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SuppressWarnings("rawtypes")
@RequiredArgsConstructor
public class CreateBookController {
    private final BookService bookService;
    private final ValidationService<CreateBookRequest> validationService;

    @PostMapping(path = "/books/create")
    public IntegrationMessage createBook(@RequestBody IntegrationMessage<CreateBookRequest> request, @AuthenticationPrincipal Person person) {
        try {
            validationService.validate(request, person);

            Book newBook = bookService.createNewBook(request.getPayload().getBookInfo());

            return IntegrationMessage.successResponse(newBook, request);
        } catch (Exception exception) {
            return IntegrationMessage.exceptionResponse(exception.getMessage(), request);
        }
    }
}
