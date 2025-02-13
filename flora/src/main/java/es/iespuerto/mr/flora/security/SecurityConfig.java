package es.iespuerto.mr.flora.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * Configuración de seguridad para la aplicación.
 * <p>Esta clase gestiona las configuraciones de seguridad, incluyendo el filtro de JWT, la gestión de CORS, la codificación de contraseñas y la autorización de rutas.</p>
 * 
 * <p>Configura la seguridad a nivel de HTTP, permitiendo o restringiendo el acceso a las rutas de la API según los roles de los usuarios, 
 * además de definir la integración con el filtro de autorización JWT.</p>
 * 
 * <p>También se configura un codificador de contraseñas utilizando el algoritmo BCrypt y se define la gestión del {@link AuthenticationManager}.</p>
 * 
 * @see JwtAuthorizationFilter
 * @see SecurityFilterChain
 * @see PasswordEncoder
 * @see AuthenticationManager
 * @see CustomUserDetailsService
 */
@Configuration
public class SecurityConfig {




    private JwtAuthorizationFilter jwtAuthorizationFilter;

    /**
     * Establece el filtro de autorización JWT.
     * 
     * @param jwtAuthorizationFilter El filtro de autorización JWT a inyectar.
     */
    @Autowired
    public void setJwtAuthorizationFilter(JwtAuthorizationFilter jwtAuthorizationFilter) {
        this.jwtAuthorizationFilter = jwtAuthorizationFilter;
    }

    /**
     * Configura el filtro de seguridad HTTP.
     * <p>Configura CORS, desactiva CSRF, define las rutas permitidas sin autenticación, 
     * agrega el filtro de autorización JWT y especifica las restricciones de seguridad para las rutas de la aplicación.</p>
     * 
     * @param http La configuración de seguridad HTTP.
     * @return Un {@link SecurityFilterChain} configurado.
     * @throws Exception Si ocurre un error durante la configuración.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) 
                .csrf(csrf -> csrf.disable()) 
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers(
                                "/",  "/index.html",
                                "/swagger-ui/**", "/swagger-ui.html",
                                "/v3/api-docs/**", "/swagger-resources/**",
                                "/configuration/**", "/swagger*/**",

                                "/v2/**", "/v3/**", "/webjars/**",
                                "/websocket*/**", "/api/auth/**",
                                "/services/**"
                        ).permitAll()
                                .requestMatchers("/auth/**").permitAll()
                                .requestMatchers("/h2-console/**").permitAll()
                                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                                .requestMatchers("/api/v1/products").hasRole("USER")
                                .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class) // Registrar el filtro de JWT
                .headers(headers -> headers.frameOptions().disable());
            
        return http.build();
    }
    
    /**
     * Configura el {@link AuthenticationManager} para la autenticación de usuarios.
     * <p>Utiliza un {@link CustomUserDetailsService} para cargar los detalles del usuario y un {@link PasswordEncoder} para codificar las contraseñas.</p>
     * 
     * @param http La configuración de seguridad HTTP.
     * @param passwordEncoder El codificador de contraseñas.
     * @param userDetailsService El servicio de detalles del usuario.
     * @return Un {@link AuthenticationManager} configurado.
     * @throws Exception Si ocurre un error durante la configuración.
     */
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder, CustomUserDetailsService userDetailsService) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder)
                .and()
                .build();
    }

    /**
     * Configura el codificador de contraseñas con el algoritmo BCrypt.
     * <p>Utiliza 12 rondas de hashing para asegurar las contraseñas de los usuarios.</p>
     * 
     * @return Un {@link PasswordEncoder} configurado.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12); // 12 rounds de hashing para mayor seguridad
    }

    /**
     * Configura las políticas de CORS para permitir solicitudes desde cualquier origen, método y encabezado.
     * <p>Esta configuración es necesaria para habilitar el intercambio de recursos entre diferentes dominios (CORS) de manera flexible.</p>
     * 
     * @return Una instancia de {@link UrlBasedCorsConfigurationSource} configurada.
     */
    @Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); 
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
