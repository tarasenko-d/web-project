package com.webproject.service.validate;

import com.webproject.dto.IntegrationMessage;
import com.webproject.dto.exception.ForbiddenException;
import com.webproject.dto.request.CreateAuthorRequest;
import com.webproject.model.Author;
import com.webproject.model.Person;
import com.webproject.model.Role;
import com.webproject.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class CreateAuthorValidator implements ValidationService<CreateAuthorRequest> {

    private final static Set<Role.RoleEnum> ACCESS_ROLES = Set.of(Role.RoleEnum.ADMIN, Role.RoleEnum.USER);
    private final AuthorService authorService;

    public void validate(IntegrationMessage<CreateAuthorRequest> request, Person person) {
        CreateAuthorRequest.AuthorInfo payload = Optional.ofNullable(request)
                .map(IntegrationMessage::getPayload)
                .map(CreateAuthorRequest::getAuthorInfo)
                .orElse(null);

        if (isNull(payload)) {
            throw new ForbiddenException("Payload is not present");
        }

        validateAccess(person.getRoles());
        validateData(payload);
    }

    private void validateData(CreateAuthorRequest.AuthorInfo authorInfo) {
        String authorName = authorInfo.getName();
        Author author = authorService.getByName(authorName);

        if (Objects.nonNull(author)) {
            throw new IllegalArgumentException(String.format("Author with name %s already saved", authorName));
        }
    }

    public Set<Role.RoleEnum> getAccessRoles() {
        return ACCESS_ROLES;
    }
}
