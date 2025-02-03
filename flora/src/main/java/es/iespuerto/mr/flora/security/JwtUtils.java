package es.iespuerto.mr.flora.security;

import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

/**
 * Clase utilitaria para generar y validar JSON Web Tokens (JWT).
 * Esta clase es responsable de crear y verificar tokens JWT, que se utilizan para la autenticación
 * y autorización en la aplicación. Utiliza el algoritmo de firma HMAC SHA-256 y una clave secreta.
 * 
 * @see <a href="https://jwt.io">JWT.io</a>
 */
@Component
public class JwtUtils {

    /**
     * La clave secreta utilizada para firmar los tokens JWT.
     * Esta clave es generada de forma aleatoria usando el algoritmo HMAC SHA-256.
     */
    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    /**
     * El tiempo de expiración del token en milisegundos (10 horas).
     */
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 10;

    /**
     * Prefijo utilizado para el encabezado de autorización del JWT.
     * Por lo general, es "Bearer ".
     */
    private static final String JWT_PREFIX = "Bearer ";

    /**
     * Obtiene los nombres de los roles a partir de una colección de autoridades.
     * 
     * @param authorities Una colección de autoridades (roles) que el usuario posee.
     * @return Una lista de cadenas con los nombres de los roles.
     */
    public List<String> getRoleNames(Collection<? extends GrantedAuthority> authorities) {
        return authorities.stream()
                          .map(GrantedAuthority::getAuthority)  // Mapea la autoridad a su nombre
                          .collect(Collectors.toList());        // Recoge los resultados en una lista
    }

    /**
     * Genera un token JWT con el nombre de usuario y los roles proporcionados.
     * 
     * @param username El nombre de usuario para el cual se genera el token.
     * @param authorities La colección de autoridades (roles) del usuario.
     * @return El token JWT generado como una cadena.
     */
    public String generateToken(String username, Collection<? extends GrantedAuthority> authorities) {
        return Jwts.builder()
                .setSubject(username)                                      // Establece el nombre de usuario como el sujeto
                .claim("roles", getRoleNames(authorities))                    // Establece los roles como una reclamación en el token
                .setIssuedAt(new Date())                                     // Establece la fecha de emisión
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))  // Establece la fecha de expiración
                .signWith(SECRET_KEY)                                        // Firma el token con la clave secreta
                .compact();                                                 // Devuelve el token compacto como una cadena
    }

    /**
     * Extrae el nombre de usuario de un token JWT.
     * 
     * @param token El token JWT del cual se extrae el nombre de usuario.
     * @return El nombre de usuario contenido en el token.
     */
    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)                                  // Establece la clave secreta para la verificación
                .build()
                .parseClaimsJws(token.replace(JWT_PREFIX, ""))              // Parsea el token después de eliminar el prefijo "Bearer "
                .getBody()
                .getSubject();                                              // Obtiene el sujeto (nombre de usuario)
    }

    /**
     * Extrae los roles del token JWT.
     * 
     * @param token El token JWT desde el cual se extraen los roles.
     * @return Una lista de roles extraídos del token.
     */
    public List<String> getRolesFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                            .setSigningKey(SECRET_KEY)                        // Establece la clave secreta para la verificación
                            .build()
                            .parseClaimsJws(token)                            // Parsea el token
                            .getBody();                                       // Obtiene el cuerpo del token
        return claims.get("roles", List.class);                          // Obtiene los roles almacenados en el token
    }

    /**
     * Valida un token JWT verificando su firma y fecha de expiración.
     * 
     * @param token El token JWT que se desea validar.
     * @return {@code true} si el token es válido, {@code false} en caso contrario.
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)                              // Establece la clave secreta para la verificación
                .build()
                .parseClaimsJws(token.replace(JWT_PREFIX, ""));         // Parsea el token después de eliminar el prefijo "Bearer "
            return true;                                               // Si no ocurre ninguna excepción, el token es válido
        } catch (Exception e) {
            return false;                                              // Si ocurre una excepción, el token no es válido
        }
    }
}
