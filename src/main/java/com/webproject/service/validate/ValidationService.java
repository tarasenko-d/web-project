package com.webproject.service.validate;

import com.webproject.dto.IntegrationMessage;
import com.webproject.dto.exception.ForbiddenException;
import com.webproject.model.Person;
import com.webproject.model.Role;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface ValidationService<T> {
    Set<Role.RoleEnum> getAccessRoles();

    void validate(IntegrationMessage<T> request, Person person);

    default void validateAccess(Set<Role> personRoles) {
        for (Role personRole : personRoles) {
            if (getAccessRoles().contains(personRole.getName())) {
                return;
            }
        }

        throw new ForbiddenException("Operation not supported for this person");
    }
}
