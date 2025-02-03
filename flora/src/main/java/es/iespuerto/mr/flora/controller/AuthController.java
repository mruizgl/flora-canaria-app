package es.iespuerto.mr.flora.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.iespuerto.mr.flora.security.CustomUserDetailsService;
import es.iespuerto.mr.flora.security.JwtUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * Controlador REST encargado de gestionar la autenticación de usuarios.
 * Proporciona un endpoint para que los usuarios inicien sesión, validando las credenciales
 * y generando un token JWT que se devuelve al usuario.
 * 
 * @see CustomUserDetailsService
 * @see JwtUtils
 * @see AuthenticationManager
 */
@RestController
@Slf4j
@RequestMapping("/auth")
public class AuthController {

    /**
     * Servicio personalizado para cargar los detalles de usuario desde la base de datos.
     */
    private CustomUserDetailsService userDetailsService;    

    /**
     * Utilidad para la generación de tokens JWT.
     */
    private JwtUtils jwtUtils;

    /**
     * Gestor de autenticación de Spring Security.
     */
    private AuthenticationManager authenticationManager;

    /**
     * Inyecta el {@link AuthenticationManager} para la gestión de la autenticación.
     * 
     * @param authenticationManager El {@link AuthenticationManager} que gestiona la autenticación.
     */
    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /**
     * Inyecta la utilidad {@link JwtUtils} para la generación de tokens JWT.
     * 
     * @param jwtUtils La utilidad que permite generar tokens JWT.
     */
    @Autowired
    public void setJwtUtils(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    /**
     * Inyecta el servicio personalizado {@link CustomUserDetailsService} para cargar los detalles de usuario.
     * 
     * @param userDetailsService El servicio que gestiona los detalles del usuario.
     */
    @Autowired
    public void setCustomUserDetailsService(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     * Endpoint para el inicio de sesión de un usuario. Valida las credenciales proporcionadas (nombre de usuario y contraseña),
     * y si son correctas, genera y devuelve un token JWT. En caso de credenciales incorrectas, devuelve un error 401.
     * 
     * @param username El nombre de usuario que el usuario quiere usar para iniciar sesión.
     * @param password La contraseña asociada al nombre de usuario.
     * @return Un {@link ResponseEntity} que contiene el token JWT en caso de éxito, o un mensaje de error en caso de fallo.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
        try {
            // Intenta autenticar las credenciales
            Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(username, password));

            // Carga los detalles del usuario desde el servicio
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            // Obtiene las autoridades del usuario (roles)
            Collection<? extends GrantedAuthority> autorities = userDetails.getAuthorities();
            // Genera el token JWT y lo devuelve
            return ResponseEntity.ok(jwtUtils.generateToken(userDetails.getUsername(), autorities));
        } catch(BadCredentialsException eb) {
            // Maneja el caso de credenciales inválidas
            log.error("Se ha producido un error por credenciales invalidas:{}", eb.getMessage());
        } catch (Exception e) {
            // Maneja cualquier otro error no controlado
            log.error("Se ha producido un error no controlado:{}", e.getMessage());
        }
        // Si las credenciales no son válidas, se responde con un error 401
        return ResponseEntity.status(401).body("Invalid username or password");
    }
}
