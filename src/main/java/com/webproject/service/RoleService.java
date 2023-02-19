package com.webproject.service;

import com.webproject.dao.RoleRepo;
import com.webproject.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepo roleRepo;

    public Role getRole(Role.RoleEnum roleName) {
        return roleRepo.findRoleByName(roleName);
    }

    public void addRole(Role.RoleEnum roleName) {
        roleRepo.save(new Role(null, roleName));
    }
}
