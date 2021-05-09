package com.plsnogod.jmboot.service;

import com.plsnogod.jmboot.model.Role;

import java.util.List;

public interface RoleService {
    Role findByRole(String nameRoles);
    List<Role> list_roles();
}
