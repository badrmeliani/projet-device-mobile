package com.valueit.device.dao;

import com.valueit.device.service.model.Emp;
import com.valueit.device.service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmpRepository extends JpaRepository<Emp,Long> {

    Emp findByUsername(String username);

    List<Emp> findBySalary(Double salary);

    List<Emp> findByFonction(String designation);

    List<Emp> findBySalaryAndFonction(Double salary, String fonction);

    @Query(" SELECT e from Emp e where e.salary=(select MAX(salary) as salary FROM Emp)")
    Emp getEmpHavaingMaxSalary();
}
