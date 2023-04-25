//package com.valueit.services;
//
//import com.valueit.device.dao.EntrepriseRepository;
//import com.valueit.device.domaine.EntrepriseConverter;
//import com.valueit.device.domaine.EntrepriseVo;
//import com.valueit.device.service.EntrepriseServiceImp;
//import com.valueit.device.service.model.Entreprise;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.Assert.*;
//
//@RunWith(MockitoJUnitRunner.class)
//public class EntrepriseServiceTests {
//
//    @Mock
//    private EntrepriseRepository entrepriseRepository;
//
//    @InjectMocks
//    private EntrepriseServiceImp entrepriseService;
//
//    private static final Long ID = 1L;
//    private static final String NOM = "ValueIt";
//    private static final String ADRESSE = "123 Test Street";
//    private static final int CAPITAL = 1000000;
//    private static final String FONDATEUR = "John Doe";
//    private static final Date DATE_CREATION = new Date();
//
//    @Test
//    public void testGetAll() {
//        // given
//        List<Entreprise> entreprises = new ArrayList<>();
//        entreprises.add(new Entreprise(ID, NOM, ADRESSE, CAPITAL, FONDATEUR, DATE_CREATION));
//        Mockito.when(entrepriseRepository.findAll()).thenReturn(entreprises);
//
//        // when
//        List<EntrepriseVo> entrepriseVo = entrepriseService.getAll();
//
//        // then
//        assertEquals(entreprises.size(), entrepriseVo.size());
//        assertEquals(EntrepriseConverter.toVo(entreprises.get(0)), entrepriseVo.get(0));
//    }
//    @Test
//    public void testSave() {
//        // given
//        EntrepriseVo entrepriseVo = new EntrepriseVo(NOM, ADRESSE, CAPITAL, FONDATEUR,DATE_CREATION);
//        Entreprise entreprise = new Entreprise(ID, NOM, ADRESSE, CAPITAL, FONDATEUR,DATE_CREATION);
//        Mockito.when(entrepriseRepository.save(Mockito.any())).thenReturn(entreprise);
//
//        // when
//        entrepriseService.save(entrepriseVo);
//
//        // then
//        Mockito.verify(entrepriseRepository, Mockito.times(1)).save(Mockito.any());
//    }
//    @Test
//    public void testGetById() {
//        // given
//        Entreprise entreprise = new Entreprise(ID, NOM, ADRESSE, CAPITAL, FONDATEUR, DATE_CREATION);
//        Mockito.when(entrepriseRepository.existsById(ID)).thenReturn(true);
//        Mockito.when(entrepriseRepository.findById(ID)).thenReturn(Optional.of(entreprise));
//
//        // when
//        EntrepriseVo entrepriseVo = entrepriseService.getById(ID);
//
//        // then
//        assertNotNull(entrepriseVo);
//        assertEquals(EntrepriseConverter.toVo(entreprise), entrepriseVo);
//    }
//    @Test
//    public void testDeleteById() {
//        // when
//        entrepriseService.deleteById(ID);
//
//        // then
//        Mockito.verify(entrepriseRepository, Mockito.times(1)).deleteById(ID);
//    }
//    @Test
//    public void testDeleteAll() {
//        // when
//        entrepriseService.deleteAll();
//
//        // then
//        Mockito.verify(entrepriseRepository, Mockito.times(1)).deleteAll();
//    }
//    @Test
//    public void testFindByNameEntreprise() {
//        // given
//        List<Entreprise> entreprises = new ArrayList<>();
//        entreprises.add(new Entreprise(ID, NOM, ADRESSE, CAPITAL, FONDATEUR, DATE_CREATION));
//        Mockito.when(entrepriseRepository.findByNom(NOM)).thenReturn(entreprises);
//
//        // when
//        List<EntrepriseVo> entrepriseVos = entrepriseService.findByNameEntreprise(NOM);
//
//        // then
//        Assert.assertEquals(entreprises.size(), entrepriseVos.size());
//        Assert.assertEquals(EntrepriseConverter.toVo(entreprises.get(0)), entrepriseVos.get(0));
//        Mockito.verify(entrepriseRepository, Mockito.times(1)).findByNom(NOM);
//        Mockito.verifyNoMoreInteractions(entrepriseRepository);
//    }
//
//}
//
