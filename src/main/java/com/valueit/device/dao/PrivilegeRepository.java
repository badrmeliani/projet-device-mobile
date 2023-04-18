package com.valueit.device.dao;

import com.valueit.device.service.model.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRepository extends JpaRepository<Privilege,Long> {
    Privilege findByPrivilege(String privilege);
}
