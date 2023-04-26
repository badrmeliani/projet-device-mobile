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
		PrivilegeVo privilege1 = new PrivilegeVo("create_users");
		PrivilegeVo privilege2 = new PrivilegeVo("delete_users");
		PrivilegeVo privilege3 = new PrivilegeVo("update_users");
		PrivilegeVo privilege4 = new PrivilegeVo("view_users");
		PrivilegeVo privilege5 = new PrivilegeVo("pagination_users");
		PrivilegeVo privilege6 = new PrivilegeVo("sort_users");
		RoleVo roleSuperAdmin = new RoleVo("SUPERADMIN") ;
		RoleVo roleAdmin = new RoleVo("ADMIN") ;
		RoleVo roleChef  = new RoleVo("CHEF");


//


//
		roleSuperAdmin.setPrivileges(Arrays.asList(privilege1,privilege2,privilege3,privilege4,privilege5,privilege6));
		roleAdmin.setPrivileges(Arrays.asList(privilege1,privilege2,privilege3,privilege4,privilege5,privilege6));
		roleChef.setPrivileges(Arrays.asList(privilege4));

		userService.save(roleAdmin);
		userService.save(roleChef);
		userService.save(roleSuperAdmin);

		UserVo admin = new UserVo("admin1", "admin1", Arrays.asList(roleAdmin),true,true,true,true);
		UserVo superadmin = new UserVo("superadmin1", "superadmin1", Arrays.asList(roleSuperAdmin),true,true,true,true);
		UserVo chef = new UserVo("chef1", "chef1", Arrays.asList(roleChef),true,true,true,true);
////		UserVo Employee1 = new UserVo("EMP", "chef1", Arrays.asList(roleEmp),true,true,true,true);
////		UserVo Employee2 = new UserVo("EMP", "chef2", Arrays.asList(roleEmp),true,true,true,true);
        		userService.save(privilege1);
		userService.save(privilege2);
		userService.save(privilege3);
		userService.save(privilege4);
		userService.save(privilege5);
		userService.save(privilege6);
		userService.save(superadmin);
		userService.save(admin);
		userService.save(chef);

//
//		iDeviceservices.save(new DeviceVo("test1","test2"));
//		userService.save(Employee1);


	}
}
