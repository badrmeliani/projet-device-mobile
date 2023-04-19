package com.valueit.services;


import com.valueit.device.dao.DeviceRepositoty;
import com.valueit.device.domaine.DeviceConverter;
import com.valueit.device.domaine.DeviceVo;
import com.valueit.device.service.DeviceServicesImp;
import com.valueit.device.service.model.Device;
import com.valueit.device.service.model.Entreprise;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class DeviceServiceTests {

    @InjectMocks
    private DeviceServicesImp deviceServicesImp;

    @Mock
    private DeviceRepositoty deviceRepositoty;

    @Test
    public void testGetAll() {
        List<Device> devices = new ArrayList<>();
        Device device1 = new Device(1L, "Apple", "iPhone", new Entreprise("Value IT"));
        Device device2 = new Device(2L, "Samsung", "Galaxy", new Entreprise("Value IT"));
        devices.add(device1);
        devices.add(device2);

        when(deviceRepositoty.findAll()).thenReturn(devices);

        List<DeviceVo> deviceVos = deviceServicesImp.getAll();

        assertEquals(deviceVos.size(), devices.size());
        assertEquals(deviceVos.get(0).getMarque(), devices.get(0).getMarque());
        assertEquals(deviceVos.get(0).getModele(), devices.get(0).getModele());
        assertEquals(deviceVos.get(1).getMarque(), devices.get(1).getMarque());
        assertEquals(deviceVos.get(1).getModele(), devices.get(1).getModele());
    }
    @Test
    public void testSave() {
        DeviceVo deviceVo = new DeviceVo(1L, "Apple", "iPhone");
        Device device = new Device(1L, "Apple", "iPhone", new Entreprise("Value IT"));

        when(deviceRepositoty.save(Mockito.any(Device.class))).thenReturn(device);

        Device result = deviceServicesImp.save(deviceVo);

        assertNotNull(result);
        assertEquals(result.getMarque(), deviceVo.getMarque());
        assertEquals(result.getModele(), deviceVo.getModele());
    }
    @Test
    public void testGetById() {
        // Create a sample entreprise object
        Entreprise entreprise = new Entreprise("Value IT");

        // Create a sample device object
        Device device = new Device();
        device.setNumSrie(1L);
        device.setMarque("Apple");
        device.setModele("iPhone");
        device.setEntreprise(entreprise);

        when(deviceRepositoty.findById(1L)).thenReturn(Optional.of(device));

        DeviceVo result = deviceServicesImp.getById(1L);

        assertNotNull(result);

        assertEquals("Apple", result.getMarque());
        assertEquals("iPhone", result.getModele());
    }
    @Test
    public void testFindByMarque() {
        Device device1 = new Device(1L, "Apple", "iPhone", new Entreprise("Value IT"));
        Device device2 = new Device(2L, "Apple", "iPad", new Entreprise("Value IT"));
        List<Device> devices = Arrays.asList(device1, device2);

        when(deviceRepositoty.findByMarque(Mockito.anyString())).thenReturn(devices);

        List<DeviceVo> result = deviceServicesImp.findByMarque("Apple");

        assertNotNull(result);
        assertEquals(2, result.size());
    }
    @Test
    public void testFindByModele() {
        Device device1 = new Device(1L, "Apple", "iPhone", new Entreprise("Value IT"));
        Device device2 = new Device(2L, "Samsung", "Galaxy", new Entreprise("Value IT"));
        List<Device> devices = Arrays.asList(device1, device2);

        when(deviceRepositoty.findByModele(Mockito.anyString())).thenReturn(devices);

        List<DeviceVo> result = deviceServicesImp.findByModele("Galaxy");

        assertNotNull(result);
        assertEquals(2, result.size());
    }
    @Test
    public void testDeleteById() {
        Mockito.doNothing().when(deviceRepositoty).deleteById(Mockito.anyLong());

        deviceServicesImp.deleteById(1L);

        Mockito.verify(deviceRepositoty, Mockito.times(1)).deleteById(Mockito.anyLong());
    }
    @Test
    public void testDeleteAll() {

        Mockito.doNothing().when(deviceRepositoty).deleteAll();


        deviceServicesImp.deleteAll();


        Mockito.verify(deviceRepositoty, Mockito.times(1)).deleteAll();
    }
    @Test
    public void testFindAll() {
        Page<Device> page = new PageImpl<>(Arrays.asList(
                new Device(1L, "Samsung", "Galaxy S21", new Entreprise("Value IT")),
                new Device(2L, "Apple", "iPhone 12", new Entreprise("Value IT")),
                new Device(3L, "Google", "Pixel 5", new Entreprise("Value IT"))
        ));

        when(deviceRepositoty.findAll(Mockito.any(PageRequest.class))).thenReturn(page);

        List<DeviceVo> result = deviceServicesImp.findAll(0, 3);

        assertNotNull(result);
        assertEquals(result.size(), 3);
        assertEquals(result.get(0).getMarque(), "Samsung");
        assertEquals(result.get(0).getModele(), "Galaxy S21");
        assertEquals(result.get(1).getMarque(), "Apple");
        assertEquals(result.get(1).getModele(), "iPhone 12");
        assertEquals(result.get(2).getMarque(), "Google");
        assertEquals(result.get(2).getModele(), "Pixel 5");
    }

    @Test
    public void testSortBy() {
        // create test devices
        Device device1 =  new Device(1L, "Samsung", "Galaxy S21", new Entreprise("Value IT"));
        Device device2 =  new Device(2L, "Google", "Pixel 5", new Entreprise("Value IT"));
        Device device3 = new Device(2L, "Apple", "iPhone 12", new Entreprise("Value IT"));

        // save devices to repository
        deviceRepositoty.save(device1);
        deviceRepositoty.save(device2);
        deviceRepositoty.save(device3);
        deviceRepositoty.flush();

        // sort devices by modele in descending order
        List<DeviceVo> sortedDevices = deviceServicesImp.sortBy("modele");

        // debug: print out sorted devices
        System.out.println("Sorted Devices:");
        for(DeviceVo device : sortedDevices) {
            System.out.println(device.getModele());
        }

        // assert that the devices are sorted in descending order by modele
        assertEquals("iPhone 12", sortedDevices.get(0).getModele());
        assertEquals("Galaxy S21", sortedDevices.get(1).getModele());
        assertEquals("Pixel 5", sortedDevices.get(2).getModele());
    }
}

