package com.valueit.device;


import com.valueit.device.domaine.PrivilegeVo;
import com.valueit.device.domaine.RoleVo;
import com.valueit.device.domaine.UserVo;
import com.valueit.device.service.IDeviceservices;
import com.valueit.device.service.IEntrepriseService;
import com.valueit.device.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.util.Arrays;


@SpringBootApplication
public class DeviceApplication implements CommandLineRunner {
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private IUserService userService;
	@Autowired
	private IDeviceservices iDeviceservices;
	@Autowired
	IEntrepriseService iEntrepriseService;




	public static void main(String[] args) {
		SpringApplication.run(DeviceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		PrivilegeVo privilege1 = new PrivilegeVo("create_article");
		PrivilegeVo privilege2 = new PrivilegeVo("delete_article");
		PrivilegeVo privilege3 = new PrivilegeVo("update_article");
		PrivilegeVo privilege4 = new PrivilegeVo("read_article");

		userService.save(new RoleVo("ADMIN"));
		userService.save(new RoleVo("CHEF"));
		userService.save(new RoleVo("EMP"));

		RoleVo roleAdmin = userService.getRoleByName("ADMIN");
		RoleVo roleChef = userService.getRoleByName("CHEF");
		RoleVo roleEmp = userService.getRoleByName("EMP");
		roleAdmin.setPrivileges(Arrays.asList(privilege1,privilege2,privilege3));
		roleChef.setPrivileges(Arrays.asList(privilege4));

		UserVo admin1 = new UserVo("admin1", "admin1", Arrays.asList(roleAdmin),true,true,true,true);
		UserVo admin2 = new UserVo("admin2", "admin2", Arrays.asList(roleAdmin),true,true,true,true);
		UserVo chef1 = new UserVo("chef1", "chef1", Arrays.asList(roleChef),true,true,true,true);
		UserVo chef2 = new UserVo("chef2", "chef2", Arrays.asList(roleChef),true,true,true,true);
		UserVo Employee1 = new UserVo("chef1", "chef1", Arrays.asList(roleChef),true,true,true,true);
		UserVo Employee2 = new UserVo("chef2", "chef2", Arrays.asList(roleChef),true,true,true,true);
//		privilegeService.save(privilege1);
//		privilegeService.save(privilege2);
//		privilegeService.save(privilege3);
//		privilegeService.save(privilege4);
		userService.save(admin1);
		userService.save(admin2);
		userService.save(chef1);
		userService.save(Employee1);


	}
}
