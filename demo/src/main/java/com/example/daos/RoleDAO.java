package com.example.daos;

import com.example.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDAO extends JpaRepository<Role, Integer> {
    Role findByName(String name);
}
