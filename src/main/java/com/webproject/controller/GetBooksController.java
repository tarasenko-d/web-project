package com.webproject.controller;

import com.webproject.dto.IntegrationMessage;
import com.webproject.dto.request.GetBooksRequest;
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
public class GetBooksController {

    private final BookService bookService;

    @PostMapping(path = "/book/getAll")
    public IntegrationMessage getBooks(@RequestBody IntegrationMessage<GetBooksRequest> request) {
        try {
            GetBooksRequest.PaginationInfo payload = Optional.ofNullable(request)
                    .map(IntegrationMessage::getPayload)
                    .map(GetBooksRequest::getPaginationInfo)
                    .orElse(null);

            if (isNull(payload)) {
                return IntegrationMessage.errorResponse("Payload is not present");
            }

            List<Book> books = bookService.getBooks(payload);

            return IntegrationMessage.successResponse(books);
        } catch (Exception exception) {
            return IntegrationMessage.exceptionResponse(exception.getMessage());
        }
    }
}
