package es.iespuerto.mr.flora;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Clase {@code ServletInitializer} que extiende {@link SpringBootServletInitializer}.
 * 
 * <p>
 * Esta clase es utilizada para configurar la aplicación Spring Boot cuando se despliega en un servidor de aplicaciones tradicional
 * como un archivo WAR. Se encarga de inicializar la aplicación Spring Boot mediante el método {@link #configure(SpringApplicationBuilder)}.
 * </p>
 * 
 * <p>Este método {@code configure} se utiliza para indicar la clase principal de la aplicación que contiene el método {@code main()},
 * en este caso {@link FloraApplication}.</p>
 * 
 * <p>Este tipo de inicialización es útil cuando la aplicación se ejecuta en un entorno de servidor servlet (como Tomcat), 
 * y no directamente desde el método principal en un entorno de Spring Boot autónomo.</p>
 * 
 * @see SpringBootServletInitializer
 * @see FloraApplication
 */
public class ServletInitializer extends SpringBootServletInitializer {

    /**
     * Configura la aplicación Spring Boot al ser desplegada en un servidor servlet.
     * 
     * @param application El {@link SpringApplicationBuilder} usado para construir la aplicación Spring.
     * @return El mismo {@link SpringApplicationBuilder} con la clase principal {@link FloraApplication} configurada.
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(FloraApplication.class);
    }
}
