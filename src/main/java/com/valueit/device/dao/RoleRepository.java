package com.valueit.device.dao;

import com.valueit.device.service.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    List<Role> findByRole(String role);

    List<Role> findAll();
    boolean existsByRole(String role);

}

