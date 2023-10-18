package com.valueit.services;



import com.valueit.device.dao.DeviceRepositoty;
import com.valueit.device.domaine.*;
import com.valueit.device.service.DeviceServicesImp;

import com.valueit.device.service.model.Device;


import com.valueit.device.service.model.Emp;
import com.valueit.device.service.model.Entreprise;
import com.valueit.device.service.model.Role;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;


import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;
import java.text.SimpleDateFormat;


import static java.util.Optional.*;
import static org.junit.Assert.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class DeviceServiceTests {

    @Mock
    private DeviceRepositoty deviceRepository;

    @InjectMocks
    private DeviceServicesImp deviceServicesImp;

    private List<Device> devices;
    private Device device;
    private DeviceVo deviceVo;


    @Before
    public void setUp() {
        String dateStr = "2000-04-01";
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        // Assuming you have a List of roles
        List<Role> roles1 = new ArrayList<>();
        Role role1 = new Role(/* construct a Role object */);
        Role role2 = new Role(/* construct another Role object */);

        roles1.add(role1);
        roles1.add(role2);

        List<Role> roles2 = new ArrayList<>();
        Role role3 = new Role(/* construct another Role object */);
        roles2.add(role3);

        Entreprise entreprise1 = new Entreprise(1L,"Company1", "Address1",10000,"That guy","IT",date,null);
        EntrepriseVo entreprise2 = new EntrepriseVo(1L,"Company1", "Address1",10000,"That guy","IT",date,null);


        Emp emp1 = new Emp("username1", "password1", roles1, true, true, true, true, "Function1", 50000.0);
        EmpVo emp2 = new EmpVo("username2", "password2", null, true, true, true, true, 60000.0,"Function2",entreprise2,null);




        devices = Arrays.asList(
                new Device(1L,"XYZ123456789", "Samsung", "Galaxy S21", entreprise1,emp1)
        );

        MockitoAnnotations.initMocks(this);

        // Now you can provide these Entreprise objects in Device and DeviceVo creation
        device = new Device(1L,"XYZ123456789", "Samsung", "Galaxy S21", entreprise1,emp1);
        deviceVo = new DeviceVo(1L,"PDQ987654321", "Samsung", "Galaxy S21", entreprise2,emp2);
    }


    @After
    public void tearDown() {
        devices = null;
        device = null;
        deviceVo = null;
    }


    @Test
    public void testGetAllDevices() {
        when(deviceRepository.findAll()).thenReturn(devices);

        List<DeviceVo> deviceVoList = deviceServicesImp.getAll();

        assertEquals(devices.size(), deviceVoList.size());
    }
    @Test
    public void testGetByIdExistingDevice() {
        when(deviceRepository.findById(1L));

        DeviceVo result = deviceServicesImp.getById(1L);

        assertEquals(deviceVo, result);
    }

    @Test
    public void testGetByIdNonExistingDevice() {
        when(deviceRepository.findById(1L)).thenReturn(empty());

        DeviceVo result = deviceServicesImp.getById(1L);

        assertNull(result);
    }
    @Test
    public void testSaveDevice() {
        DeviceVo deviceVo = new DeviceVo();
        deviceVo.setMarque("Huawei");
        deviceVo.setModele("P40");

        when(deviceRepository.save(any(Device.class))).thenReturn(DeviceConverter.toBe(deviceVo));

        Device savedDevice = deviceServicesImp.save(deviceVo);

        assertNotNull(savedDevice);
        assertEquals(deviceVo.getMarque(), savedDevice.getMarque());
        assertEquals(deviceVo.getModele(), savedDevice.getModele());

    }
    @Test
    public void testGetDevicesByMarque() {
        String marque = "Samsung";
        List<Device> marqueDevices = devices.stream().filter(device -> device.getMarque().equals(marque)).collect(Collectors.toList());
        when(deviceRepository.findByMarque(marque)).thenReturn(marqueDevices);

        List<DeviceVo> deviceVoList = deviceServicesImp.findByMarque(marque);

        assertEquals(marqueDevices.size(), deviceVoList.size());
    }
    @Test
    public void testGetDevicesByModele() {
        String modele = "Galaxy S23";
        List<Device> modeleDevices = devices.stream().filter(device -> device.getModele().equals(modele)).collect(Collectors.toList());
        when(deviceRepository.findByModele(modele)).thenReturn(modeleDevices);

        List<DeviceVo> deviceVoList = deviceServicesImp.findByModele(modele);

        assertEquals(modeleDevices.size(), deviceVoList.size());
    }

    @Test
    public void testDeleteById() {
        Long idToDelete = 1L;
        deviceServicesImp.deleteById(idToDelete);
        verify(deviceRepository, times(1)).deleteById(idToDelete);
    }

    @Test
    public void testDeleteAll() {
        deviceServicesImp.deleteAll();
        verify(deviceRepository, times(1)).deleteAll();
    }

    @Test
    public void testFindAll() {
        int pageId = 0;
        int size = 10;
        Page<Device> devicesPage = new PageImpl<>(devices);
        when(deviceRepository.findAll(PageRequest.of(pageId, size))).thenReturn(devicesPage);

        List<DeviceVo> deviceVos = deviceServicesImp.findAll(pageId, size);

        assertEquals(devices.size(), deviceVos.size());
        for (int i = 0; i < devices.size(); i++) {
            Device device = devices.get(i);
            DeviceVo deviceVo = deviceVos.get(i);
            assertEquals(device.getNumSrie(), deviceVo.getNumSrie());
            assertEquals(device.getMarque(), deviceVo.getMarque());
            assertEquals(device.getModele(), deviceVo.getModele());
        }
    }
    @Test
    public void testSortBy() {
        String sortBy = "modele";
        Sort sort = Sort.by(Sort.Direction.DESC, sortBy);
        when(deviceRepository.findAll(sort)).thenReturn(devices);

        List<DeviceVo> deviceVos = deviceServicesImp.sortBy(sortBy);

        assertEquals(devices.size(), deviceVos.size());
        for (int i = 0; i < devices.size(); i++) {
            Device device = devices.get(i);
            DeviceVo deviceVo = deviceVos.get(i);
            assertEquals(device.getNumSrie(), deviceVo.getNumSrie());
            assertEquals(device.getMarque(), deviceVo.getMarque());
            assertEquals(device.getModele(), deviceVo.getModele());
        }
    }


}