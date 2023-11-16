package com.nova.todomanagmentproject.repository;

import com.nova.todomanagmentproject.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
