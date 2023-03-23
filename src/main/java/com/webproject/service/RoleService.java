package com.webproject.service;

import com.webproject.dao.RoleRepo;
import com.webproject.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepo roleRepo;

    public Role getRole(Role.RoleEnum roleName) {
        Optional<Role> modelOptional = roleRepo.findRoleByName(roleName);

        if (modelOptional.isEmpty()) {
            String message = String.format("Role with name %s not found", roleName);
            throw new EntityNotFoundException(message);
        }

        return modelOptional.get();
    }

    public void addRole(Role.RoleEnum roleName) {
        roleRepo.save(new Role(null, roleName));
    }
}
