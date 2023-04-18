package com.valueit.device.dao;

import com.valueit.device.service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsername(String name);
    boolean existsByUsername(String username);

}