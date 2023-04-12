package com.valueit.device.dao;

import com.valueit.device.service.model.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EntrepriseRepository extends JpaRepository<Entreprise,Long> {
    List<Entreprise> findByNom(String name);
}
