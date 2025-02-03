package es.iespuerto.mr.flora.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import es.iespuerto.mr.flora.model.User;
import es.iespuerto.mr.flora.repository.UserRepository;

/**
 * Servicio personalizado que implementa {@link UserDetailsService} para cargar los detalles de usuario
 * a partir de la base de datos. Este servicio obtiene los detalles del usuario desde un repositorio y
 * los utiliza para autenticar a los usuarios en el sistema.
 * 
 * @see UserRepository
 * @see UserDetails
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    /**
     * Repositorio de usuarios utilizado para acceder a la base de datos y recuperar la información de los usuarios.
     */
    private UserRepository userRepository;

    /**
     * Constructor por defecto de la clase.
     */
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Inyecta el repositorio de usuarios {@link UserRepository} en la clase.
     * 
     * @param userRepository El repositorio que gestiona la información de usuarios en la base de datos.
     */
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Carga un {@link UserDetails} a partir del nombre de usuario proporcionado. Este método es utilizado
     * por Spring Security para autenticar y autorizar a los usuarios en el sistema.
     * 
     * @param username El nombre de usuario que se utilizará para buscar al usuario en la base de datos.
     * @return Un objeto {@link UserDetails} que contiene la información de usuario necesaria para la autenticación.
     * @throws UsernameNotFoundException Si no se encuentra un usuario con el nombre de usuario proporcionado.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Busca al usuario en la base de datos por su nombre
        User user = userRepository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        // Devuelve un objeto UserDetails con la información del usuario
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getName()) // Nombre de usuario
                .password(user.getPassword()) // Contraseña
                .authorities(user.getRole().getName().toUpperCase()) // Rol del usuario (convertido a mayúsculas)
                .build();
    }
}
