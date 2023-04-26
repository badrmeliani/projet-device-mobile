package com.valueit.device;


import com.valueit.device.domaine.DeviceVo;
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
//	  userService.cleanDataBase();
//
		PrivilegeVo privilege1 = new PrivilegeVo("create_article");
		PrivilegeVo privilege2 = new PrivilegeVo("delete_article");
		PrivilegeVo privilege3 = new PrivilegeVo("update_article");
		PrivilegeVo privilege4 = new PrivilegeVo("read_article");
		RoleVo roleAdmin = new RoleVo("ADMIN") ;
		RoleVo roleChef  = new RoleVo("CHEF");
//


//
		roleAdmin.setPrivileges(Arrays.asList(privilege1,privilege2,privilege3));
		roleChef.setPrivileges(Arrays.asList(privilege4));

		userService.save(roleAdmin);
		userService.save(roleChef);
		userService.save(new RoleVo("EMP"));

		UserVo admin1 = new UserVo("admin1", "admin1", Arrays.asList(roleAdmin),true,true,true,true);
		UserVo admin2 = new UserVo("admin2", "admin2", Arrays.asList(roleAdmin),true,true,true,true);
		UserVo chef1 = new UserVo("chef1", "chef1", Arrays.asList(roleChef),true,true,true,true);
		UserVo chef2 = new UserVo("chef2", "chef2", Arrays.asList(roleChef),true,true,true,true);
////		UserVo Employee1 = new UserVo("EMP", "chef1", Arrays.asList(roleEmp),true,true,true,true);
////		UserVo Employee2 = new UserVo("EMP", "chef2", Arrays.asList(roleEmp),true,true,true,true);
        		userService.save(privilege1);
		userService.save(privilege2);
		userService.save(privilege3);
		userService.save(privilege4);
		userService.save(admin1);
		userService.save(admin2);
		userService.save(chef1);

//
//		iDeviceservices.save(new DeviceVo("test1","test2"));
//		userService.save(Employee1);


	}
}
