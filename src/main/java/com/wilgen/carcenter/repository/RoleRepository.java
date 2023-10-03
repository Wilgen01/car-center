package com.wilgen.carcenter.repository;

import com.wilgen.carcenter.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
