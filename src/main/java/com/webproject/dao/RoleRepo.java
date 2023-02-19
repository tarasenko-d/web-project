package com.webproject.dao;

import com.webproject.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {

    Role findRoleByName(Role.RoleEnum name);
}
