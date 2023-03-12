package com.webproject.dto;

import com.webproject.dto.response.GetBookByIdResponse;
import com.webproject.dto.response.GetBooksResponse;
import com.webproject.dto.response.GetNAuthorsAlphabetResponse;
import com.webproject.dto.response.GetUserByIdResponse;
import com.webproject.model.Author;
import com.webproject.model.Book;
import com.webproject.model.Person;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
public class IntegrationMessage<T> {
    private Status status;
    private T payload;
    private Fault fault;

    public static IntegrationMessage successResponse(List list) {
        return successResponse(list);
    }

    public static IntegrationMessage<GetUserByIdResponse> successResponse(Person person) {
        return successResponse(person);
    }

    public static IntegrationMessage<GetBookByIdResponse> successResponse(Book book) {
        return successResponse(book);
    }

    public static <T> IntegrationMessage<T> exceptionResponse(String message) {
        IntegrationMessage<T> response = new IntegrationMessage<>();

        Fault fault = new Fault();
        fault.setMessage(message);

        response.setStatus(Status.EXCEPTION);
        response.setFault(fault);

        return response;
    }

    public static <T> IntegrationMessage<T> errorResponse(String message) {
        IntegrationMessage<T> response = new IntegrationMessage<>();

        Fault fault = new Fault();
        fault.setMessage(message);

        response.setStatus(Status.ERROR);
        response.setFault(fault);

        return response;
    }

    private static <T> IntegrationMessage<T> successResponse(T data) {
        IntegrationMessage<T> response = new IntegrationMessage<>();

        response.setPayload(data);
        response.setStatus(Status.SUCCESS);

        return response;
    }

    @RequiredArgsConstructor
    public enum Status {
        SUCCESS("success"),
        ERROR("error"),
        EXCEPTION("exception");

        private final String value;
    }

    @Data
    public static class Fault {
        private String message;
    }
}
