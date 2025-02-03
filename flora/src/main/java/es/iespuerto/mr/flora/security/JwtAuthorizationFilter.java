package es.iespuerto.mr.flora.security;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Filtro de autorización basado en JWT que se encarga de verificar el token de autenticación
 * en cada solicitud HTTP y establecer la autenticación del usuario en el contexto de seguridad.
 * Este filtro procesa las solicitudes entrantes, extrae y valida el token JWT, y si es válido,
 * establece la autenticación del usuario en el contexto de seguridad de Spring.
 * 
 * @see JwtUtils
 */
@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    /**
     * Utilidad para generar, validar y extraer información de tokens JWT.
     */
    private JwtUtils jwtUtils;

    /**
     * Inyecta la dependencia de {@link JwtUtils}.
     * 
     * @param jwtUtils El servicio utilitario para gestionar tokens JWT.
     */
    @Autowired
    public void setJwtUtils(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    /**
     * Método que se ejecuta para filtrar las solicitudes HTTP entrantes. Extrae el token JWT
     * de la cabecera de autorización, lo valida y, si es válido, establece la autenticación
     * del usuario en el contexto de seguridad.
     * 
     * @param request La solicitud HTTP entrante.
     * @param response La respuesta HTTP.
     * @param chain La cadena de filtros que procesa la solicitud.
     * @throws ServletException Si ocurre un error en el procesamiento de la solicitud.
     * @throws IOException Si ocurre un error de entrada/salida durante el procesamiento de la solicitud.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        // Verifica si el encabezado de autorización contiene el prefijo "Bearer "
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            // Extrae el token JWT del encabezado de autorización
            String token = authHeader.substring(7);

            // Valida el token JWT
            if (jwtUtils.validateToken(token)) {
                // Extrae el nombre de usuario del token
                String username = jwtUtils.getUsernameFromToken(token);

                // Crea un objeto de autenticación con el nombre de usuario y sus roles
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(username, null, extractAuthoritiesFromToken(token));

                // Establece los detalles de la autenticación
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Establece la autenticación en el contexto de seguridad de Spring
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        // Pasa la solicitud al siguiente filtro en la cadena
        chain.doFilter(request, response);
    }

    /**
     * Extrae las autoridades (roles) del token JWT.
     * 
     * @param token El token JWT del cual se extraen los roles.
     * @return Una lista de objetos {@link GrantedAuthority} que representan los roles del usuario.
     */
    public List<GrantedAuthority> extractAuthoritiesFromToken(String token) {
        List<String> roles = jwtUtils.getRolesFromToken(token);

        // Convierte la lista de roles a una lista de objetos {@link GrantedAuthority}
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role))  
                .collect(Collectors.toList());
    }
}
