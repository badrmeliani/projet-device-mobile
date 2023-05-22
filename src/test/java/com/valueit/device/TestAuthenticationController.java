package com.valueit.device;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.valueit.device.controller.rest.AuthenticationController;
import com.valueit.device.domaine.UserVo;
import com.valueit.device.jwt.AuthEntryPointJwt;
import com.valueit.device.jwt.JwtUtils;
import com.valueit.device.service.IEmpService;
import com.valueit.device.service.IUserService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AuthenticationController.class)

public class TestAuthenticationController {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    AuthenticationManager authenticationManager;
    @MockBean
    private IUserService userService;
    @MockBean
    private JwtUtils jwtUtils;
    @MockBean
    AuthEntryPointJwt authEntryPointJwt;
    @MockBean
    IEmpService empService;
    @Test
    void testauthenticateUser() throws Exception {
        String tokenTest = "AAAA.BBBB.SSSS";
        UserVo userVoTest = new UserVo();
        userVoTest.setUsername("admin");
        userVoTest.setPassword("admin");
        Authentication authenticationResult = new UsernamePasswordAuthenticationToken(userVoTest.getUsername(),
                userVoTest.getPassword(), Arrays.asList(new SimpleGrantedAuthority("ADMIN")));
        when(authenticationManager.authenticate(Mockito.any())).thenReturn(authenticationResult);
        when(jwtUtils.generateJwtToken(Mockito.any())).thenReturn(tokenTest);
        mockMvc.perform(post("/auth/signin").content(asJsonString(userVoTest)).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$.jwttoken").value(tokenTest))
                .andExpect(jsonPath("$.username").value(userVoTest.getUsername()))
                .andExpect(jsonPath("$.role").value("ADMIN"));
    }
    @Test
    void testregisterUser_ExistDeja() throws Exception {
        UserVo userVoTest = new UserVo();
        userVoTest.setUsername("admin");
        userVoTest.setPassword("admin");
        when(userService.existsByUsername(userVoTest.getUsername())).thenReturn(true);
        mockMvc.perform(post("/auth/signup").content(asJsonString(userVoTest)).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").value("Error: Username is already taken!"));
    }
    @Test
    @Disabled
    void testregisterUser_DoesntExist() throws Exception {
        UserVo userVoTest = new UserVo();
        userVoTest.setUsername("admin");
        userVoTest.setPassword("admin");
        when(userService.existsByUsername(userVoTest.getUsername())).thenReturn(false);
        doNothing().when(userService).save(userVoTest);
        mockMvc.perform(post("/auth/signup").content(asJsonString(userVoTest)).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk())
                .andExpect(jsonPath("$").value("User registered successfully!"));
    }
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
