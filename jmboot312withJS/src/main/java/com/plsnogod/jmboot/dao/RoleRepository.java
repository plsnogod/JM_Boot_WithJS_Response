package com.plsnogod.jmboot.dao;

import com.plsnogod.jmboot.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository <Role, Long>{
    Role findRoleByNameRoles (String nameRoles);
}
