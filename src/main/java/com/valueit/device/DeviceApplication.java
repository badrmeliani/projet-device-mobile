package com.valueit.device;


import com.valueit.device.domaine.*;
import com.valueit.device.jwt.JwtUtils;
import com.valueit.device.service.IDeviceservices;
import com.valueit.device.service.IEmpService;
import com.valueit.device.service.IEntrepriseService;
import com.valueit.device.service.IUserService;
import com.valueit.device.service.model.Emp;
import com.valueit.device.service.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.util.Arrays;


@SpringBootApplication
public class DeviceApplication  {


    public static void main(String[] args) {
        SpringApplication.run(DeviceApplication.class, args);

    }
}
