package com.valueit.device;

import com.valueit.device.dao.DeviceRepositoty;
import com.valueit.device.domaine.DeviceVo;
import com.valueit.device.service.IDeviceservices;
import com.valueit.device.service.model.Device;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Sql(scripts = {"classpath:device.sql"},executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//@Sql(scripts = {"classpath:device.sql"},executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)

class DeviceApplicationTests {
	@Autowired
	DeviceRepositoty deviceRepositoty;
	@Autowired
	IDeviceservices iDeviceservices;

	@Test
	void contextLoads() {
		assertThat(iDeviceservices).isNotNull();
//      String marque = "marque1";
//	  String modele = "modeleTets";
//	  DeviceVo  deviceVo = new DeviceVo(marque,modele);
//	  iDeviceservices.save(deviceVo);
//	  List<DeviceVo> devices = iDeviceservices.findByMarque(marque);
//
//	  assertThat(devices).hasSize(2);
	}


}
