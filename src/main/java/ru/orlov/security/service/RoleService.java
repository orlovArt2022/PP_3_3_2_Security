package ru.orlov.security.service;

import ru.orlov.security.model.Role;
import ru.orlov.security.model.User;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();

    void addRole(Role role);

    Role findByName(String name);
}
