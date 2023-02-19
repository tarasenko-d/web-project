package com.webproject.controller;

import com.webproject.dto.IntegrationMessage;
import com.webproject.dto.request.GetBookByIdRequest;
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
public class GetBookByIdController {

    private final BookService bookService;

    @PostMapping(path = "/book/getById")
    public IntegrationMessage getBookById(@RequestBody IntegrationMessage<GetBookByIdRequest> request) {
        try {
            Long payload = Optional.ofNullable(request)
                    .map(IntegrationMessage::getPayload)
                    .map(GetBookByIdRequest::getBookId)
                    .orElse(null);

            if (isNull(payload)) {
                return IntegrationMessage.errorResponse("Payload is not present");
            }

            Book book = bookService.getById(payload);

            return IntegrationMessage.successResponse(book);
        } catch (Exception exception) {
            return IntegrationMessage.exceptionResponse(exception.getMessage());
        }
    }
}
