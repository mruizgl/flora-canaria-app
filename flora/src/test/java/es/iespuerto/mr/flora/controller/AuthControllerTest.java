package es.iespuerto.mr.flora.controller;

import es.iespuerto.mr.flora.repository.UserRepository;
import es.iespuerto.mr.flora.security.CustomUserDetailsService;
import es.iespuerto.mr.flora.security.JwtUtils;
import es.iespuerto.mr.flora.model.User;
import es.iespuerto.mr.flora.model.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthController authController;

    private JwtUtils jwtUtils;
    private CustomUserDetailsService userDetailsService;
    private String username = "user";
    private String password = "password";

    @BeforeEach
    void setUp() {
        jwtUtils = new JwtUtils();
        userDetailsService = new CustomUserDetailsService(userRepository);
        authController.setJwtUtils(jwtUtils);
        authController.setCustomUserDetailsService(userDetailsService);
    }


    @Test
    void loginWithInvalidCredentialsReturnsUnauthorized() {
        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenThrow(new BadCredentialsException("Invalid credentials"));

        ResponseEntity<?> response = authController.login(username, password);

        assertEquals(401, response.getStatusCodeValue());
        assertEquals("Invalid username or password", response.getBody());
    }

    @Test
    void loginWithUnexpectedErrorReturnsUnauthorized() {
        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenThrow(new RuntimeException("Unexpected error"));

        ResponseEntity<?> response = authController.login(username, password);

        assertEquals(401, response.getStatusCodeValue());
        assertEquals("Invalid username or password", response.getBody());
    }
}