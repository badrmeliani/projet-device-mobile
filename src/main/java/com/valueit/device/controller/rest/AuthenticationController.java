package com.valueit.device.controller.rest;

import com.valueit.device.domaine.RoleVo;
import com.valueit.device.domaine.TokenVo;
import com.valueit.device.domaine.UserVo;
import com.valueit.device.jwt.JwtUtils;
import com.valueit.device.service.IUserService;
import com.valueit.device.service.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private IUserService userService;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/signin")

    public ResponseEntity<TokenVo> authenticateUser(@Valid @RequestBody UserVo userVo) {

        try {


            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(userVo.getUsername(), userVo.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            TokenVo tokenVo = new TokenVo();
            tokenVo.setJwttoken(jwt);
            tokenVo.setUsername(userVo.getUsername());
            Collection<? extends GrantedAuthority> list = authentication.getAuthorities();
            list.forEach(authorite -> tokenVo.getRoles().add(authorite.getAuthority()));
            return ResponseEntity.ok(tokenVo);
        } catch (Exception e) {
            throw new BusinessException("Login ou mot de passe incorrect");
        }

    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserVo userVo) {
        if (userService.existsByUsername(userVo.getUsername())) {
            return ResponseEntity.badRequest().body("Error: Username is already taken!");
        }
        // par défaut le client a le rôle CLIENT
        userVo.getRoles().add(new RoleVo("ADMIN"));
        userService.save(userVo);
        return ResponseEntity.ok("User registered successfully!");
    }
}
