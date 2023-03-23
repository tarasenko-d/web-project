package com.webproject.service.validate;

import com.webproject.dto.IntegrationMessage;
import com.webproject.dto.exception.ForbiddenException;
import com.webproject.dto.request.CreateBookRequest;
import com.webproject.model.Book;
import com.webproject.model.Person;
import com.webproject.model.Role;
import com.webproject.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class CreateBookValidator implements ValidationService<CreateBookRequest> {

    private final static Set<Role.RoleEnum> ACCESS_ROLES = Set.of(Role.RoleEnum.ADMIN, Role.RoleEnum.USER);
    private final BookService bookService;

    public void validate(IntegrationMessage<CreateBookRequest> request, Person person) {
        CreateBookRequest.BookInfo payload = Optional.ofNullable(request)
                .map(IntegrationMessage::getPayload)
                .map(CreateBookRequest::getBookInfo)
                .orElse(null);

        if (isNull(payload)) {
            throw new ForbiddenException("Payload is not present");
        }

        validateAccess(person.getRoles());
        validateData(payload);
    }

    private void validateData(CreateBookRequest.BookInfo bookInfo) {
        String title = bookInfo.getTitle();
        String author = bookInfo.getAuthor();
        Book book = bookService.getBooksByTitleAndAuthor(title, author);

        if (Objects.nonNull(book)) {
            throw new IllegalArgumentException(String.format("Book with title %s and author %s already saved", title, author));
        }
    }

    public Set<Role.RoleEnum> getAccessRoles() {
        return ACCESS_ROLES;
    }
}
