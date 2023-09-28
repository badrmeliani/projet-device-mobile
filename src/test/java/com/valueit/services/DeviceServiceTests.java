package com.valueit.services;



import com.valueit.device.dao.DeviceRepositoty;
import com.valueit.device.domaine.DeviceConverter;
import com.valueit.device.domaine.DeviceVo;
import com.valueit.device.service.DeviceServicesImp;

import com.valueit.device.service.model.Device;


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


import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


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
        devices = Arrays.asList(
                new Device(1L, "Samsung", "Galaxy S21", null),
                new Device(2L, "Apple", "iPhone 12", null)
        );
        MockitoAnnotations.initMocks(this);
        device = new Device(1L, "Samsung", "Galaxy S21",null);
        deviceVo = new DeviceVo(1L, "Samsung", "Galaxy S21",null);
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
        when(deviceRepository.findById(1L)).thenReturn(Optional.of(device));

        DeviceVo result = deviceServicesImp.getById(1L);

        assertEquals(deviceVo, result);
    }

    @Test
    public void testGetByIdNonExistingDevice() {
        when(deviceRepository.findById(1L)).thenReturn(Optional.empty());

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