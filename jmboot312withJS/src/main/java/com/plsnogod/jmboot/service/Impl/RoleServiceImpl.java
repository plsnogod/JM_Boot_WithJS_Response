package com.plsnogod.jmboot.service.Impl;

import com.plsnogod.jmboot.model.Role;
import com.plsnogod.jmboot.dao.RoleRepository;
import com.plsnogod.jmboot.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Transactional
    public Role findByRole(String nameRoles) {
        return roleRepository.findRoleByNameRoles(nameRoles);
    }

    @Override
    @Transactional
    public List<Role> list_roles() {
        return roleRepository.findAll();
    }
}
