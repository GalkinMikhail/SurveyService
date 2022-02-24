package com.example.fabriquetesttask.controller;

import com.example.fabriquetesttask.controller.urls.Urls;
import com.example.fabriquetesttask.dto.AuthenticationRequestDTO;
import com.example.fabriquetesttask.model.Roles;
import com.example.fabriquetesttask.model.User;
import com.example.fabriquetesttask.repository.RoleRepository;
import com.example.fabriquetesttask.repository.UserRepository;
import com.example.fabriquetesttask.security.JwtTokenProvider;
import com.example.fabriquetesttask.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("auth")
public class AuthenticationRestController {


    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    private final RoleRepository roleRepository;

    @Autowired
    public AuthenticationRestController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService, UserRepository userRepository, RoleRepository roleRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequestDTO requestDTO) {
        try {
            String username = requestDTO.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,requestDTO.getPassword()));
            User user = userService.findByUsername(username);

            if(user == null){
                user = new User();
                user.setUsername(requestDTO.getUsername());
                user.setPassword(requestDTO.getPassword());
                Set<Roles> roles = new HashSet<>();
                Roles role = roleRepository.findByName("USER");
                roles.add(role);
                user.setRoles(roles);
                userService.save(user);
            }

            String token = jwtTokenProvider.createToken(username,user.getRoles());
            Map<Object,Object> response = new HashMap<>();
            response.put("login", requestDTO.getUsername());
            response.put("token", token);
            response.put("Unique id", user.getId());
            return ResponseEntity.ok(response);

        }
        catch (AuthenticationException e){
            return new ResponseEntity<>("Invalid login or password", HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("logout")
    public void logout(HttpServletRequest request, HttpServletResponse response){
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request,response,null);
    }
}
