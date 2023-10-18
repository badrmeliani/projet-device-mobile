package com.valueit.device.service;

import com.valueit.device.dao.EntrepriseRepository;
import com.valueit.device.domaine.*;
import com.valueit.device.service.model.Entreprise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service("entreprise_service")
@Transactional
public class EntrepriseServiceImp implements IEntrepriseService {
    @Autowired
  private   EntrepriseRepository entrepriseRepository;
    @Override
    public List<EntrepriseVo> getAll() {
        List<Entreprise> list = entrepriseRepository.findAll();
        return EntrepriseConverter.toListVo(list);
    }

    @Override
    public long getCount() {
        return entrepriseRepository.count();
    }

    @Override
    public void save(EntrepriseVo entrepriseVo) {
        entrepriseRepository.save(EntrepriseConverter.toBo(entrepriseVo));

    }

    @Override
    public EntrepriseVo getById(Long id) {
        Boolean trouver = entrepriseRepository.existsById(id);
        if (!trouver) return null;

        return EntrepriseConverter.toVo(entrepriseRepository.findById(id).get());
    }

    @Override
    public void deleteById(Long id) {
     entrepriseRepository.deleteById(id);

    }

    @Override
    public void deleteAll() {
      entrepriseRepository.deleteAll();
    }

    @Override
    public List<EntrepriseVo> findByNameEntreprise(String name) {
        List<Entreprise> list = entrepriseRepository.findByNom(name);
        return EntrepriseConverter.toListVo(list);
    }

    @Override
    public List<EntrepriseVo> findAll(int pageId, int size) {
        Page<Entreprise> list = entrepriseRepository.findAll(PageRequest.of(pageId,size));
        return EntrepriseConverter.toListVo(list.getContent());
    }

    @Override
    public List<EntrepriseVo> sortBy(String nom) {
        List<Entreprise> list = entrepriseRepository.findAll(Sort.by(Sort.Direction.DESC,nom));
        return EntrepriseConverter.toListVo(list);
    }


}
