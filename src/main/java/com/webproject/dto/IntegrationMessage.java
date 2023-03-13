package com.webproject.dto;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Optional;

@Data
public class IntegrationMessage<T> {
    private Context context;
    private T payload;
    private Status status;
    private Fault fault;

    public static <T> IntegrationMessage<T> successResponse(T data, IntegrationMessage request) {
        IntegrationMessage<T> response = new IntegrationMessage<>();

        response.setPayload(data);

        Context context = new Context();
        context.setResponse(new Context.Response().setCorrelationId(getMessageUid(request)));

        response.setStatus(Status.SUCCESS);
        response.setContext(context);

        return response;
    }

    public static <T> IntegrationMessage<T> exceptionResponse(String message, IntegrationMessage request) {
        IntegrationMessage<T> response = new IntegrationMessage<>();

        Fault fault = new Fault();
        fault.setDescription(message);

        Context context = new Context();
        context.setResponse(new Context.Response().setCorrelationId(getMessageUid(request)));

        response.setStatus(Status.EXCEPTION);
        response.setFault(fault);
        response.setContext(context);

        return response;
    }

    public static <T> IntegrationMessage<T> errorResponse(String message, IntegrationMessage request) {
        IntegrationMessage<T> response = new IntegrationMessage<>();

        Fault fault = new Fault();
        fault.setDescription(message);

        Context context = new Context();

        context.setResponse(new Context.Response().setCorrelationId(getMessageUid(request)));

        response.setStatus(Status.ERROR);
        response.setFault(fault);
        response.setContext(context);

        return response;
    }

    private static String getMessageUid(IntegrationMessage request) {
        return Optional.ofNullable(request)
                .map(IntegrationMessage::getContext)
                .map(Context::getRequest)
                .map(Context.Request::getMessageUid)
                .orElse("");
    }

    @RequiredArgsConstructor
    private enum Status {
        SUCCESS("sucess"),
        ERROR("error"),
        EXCEPTION("exception");

        @Getter
        private final String value;
    }

    @Data
    @Accessors(chain = true)
    private static class Fault {
        private String description;
    }

    @Data
    @Accessors(chain = true)
    private static class Context {
        private Request request;
        private Response response;

        @Data
        @Accessors(chain = true)
        private static class Request {
            private String messageUid;
        }

        @Data
        @Accessors(chain = true)
        private static class Response {
            private String correlationId;
        }
    }
}
