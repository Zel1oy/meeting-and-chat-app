package com.app.lab7.repository;

import com.app.lab7.enums.RoleName;
import com.app.lab7.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role getRoleByName(RoleName roleName);
}
