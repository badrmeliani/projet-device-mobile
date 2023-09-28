package com.valueit.device.service;

import com.valueit.device.domaine.DeviceVo;
import com.valueit.device.domaine.EntrepriseVo;
import com.valueit.device.domaine.UserVo;

import java.util.List;

public interface IEntrepriseService {
    List<EntrepriseVo> getAll();
    void save(EntrepriseVo entrepriseVo);
    EntrepriseVo getById(Long id);
    void deleteById(Long id);
    void deleteAll();
    List<EntrepriseVo> findByNameEntreprise(String name);
    List<EntrepriseVo> findAll(int pageId, int size);

    List<EntrepriseVo> sortBy(String nom);


}
