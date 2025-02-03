package es.iespuerto.mr.flora.security;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import io.jsonwebtoken.security.Keys;

public class JwtUtilsTest {

    private JwtUtils jwtUtils;
    private Key secretKey;

    @BeforeEach
    public void setUp() {
        secretKey = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);
        jwtUtils = new JwtUtils();
    }

    @Test
    void generateTokenShouldReturnValidToken() {
        Collection<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
        String token = jwtUtils.generateToken("testUser", authorities);

        assertNotNull(token);
        assertTrue(jwtUtils.validateToken(token));
    }

    @Test
    void getUsernameFromTokenShouldReturnCorrectUsername() {
        Collection<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
        String token = jwtUtils.generateToken("testUser", authorities);

        String username = jwtUtils.getUsernameFromToken(token);
        assertEquals("testUser", username);
    }

    @Test
    void getRolesFromTokenShouldReturnCorrectRoles() {
        Collection<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
        String token = jwtUtils.generateToken("testUser", authorities);

        List<String> roles = jwtUtils.getRolesFromToken(token);
        assertEquals(1, roles.size());
        assertEquals("ROLE_USER", roles.get(0));
    }

    @Test
    void validateTokenShouldReturnTrueForValidToken() {
        Collection<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
        String token = jwtUtils.generateToken("testUser", authorities);

        assertTrue(jwtUtils.validateToken(token));
    }

    @Test
    void validateTokenShouldReturnFalseForInvalidToken() {
        String invalidToken = "invalidToken";

        assertFalse(jwtUtils.validateToken(invalidToken));
    }
}