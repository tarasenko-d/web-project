package com.webproject.controller;

import com.webproject.dto.IntegrationMessage;
import com.webproject.dto.request.GetUserByIdRequest;
import com.webproject.model.Person;
import com.webproject.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static java.util.Objects.isNull;

@RestController
@SuppressWarnings("rawtypes")
@RequiredArgsConstructor
public class GetUserByIdController {

    private final PersonService personService;

    @PostMapping(path = "/user/getById")
    public IntegrationMessage getUserById(@RequestBody IntegrationMessage<GetUserByIdRequest> request) {
        try {
            Long payload = Optional.ofNullable(request)
                    .map(IntegrationMessage::getPayload)
                    .map(GetUserByIdRequest::getUserId)
                    .orElse(null);

            if (isNull(payload)) {
                return IntegrationMessage.errorResponse("Payload is not present");
            }

            Person person = personService.getById(payload);

            return IntegrationMessage.successResponse(person);
        } catch (Exception exception) {
            return IntegrationMessage.exceptionResponse(exception.getMessage());
        }
    }
}

