package es.iespuerto.mr.flora.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

/**
 * Configuración de Swagger para la documentación de la API.
 * 
 * <p>Esta clase proporciona la configuración para integrar Swagger con la API utilizando Springdoc OpenAPI.</p>
 * 
 * <p>Swagger es una herramienta que permite generar documentación interactiva de las APIs RESTful. Con esta configuración, 
 * se define el esquema de seguridad utilizado para la autenticación mediante JWT y se configura la información básica 
 * de la API como el título, la descripción y la versión.</p>
 * 
 * <p>La seguridad de la API se define utilizando el esquema "bearerAuth", que es un tipo de autenticación HTTP basado en tokens JWT.</p>
 * 
 * <p>Los principales componentes de la configuración incluyen:</p>
 * <ul>
 *   <li>Definición de la seguridad utilizando un esquema HTTP "bearer" para JWT.</li>
 *   <li>Información básica sobre la API, como el título, la descripción y la versión.</li>
 * </ul>
 * 
 * @see OpenAPI
 * @see Info
 * @see SecurityRequirement
 * @see SecurityScheme
 */
@Configuration
public class SwaggerConfig {

    /**
     * Configura la documentación de la API utilizando Swagger.
     * <p>Este método define los componentes de seguridad, como el esquema de autenticación JWT, y agrega la información
     * básica de la API, como el título, la descripción y la versión.</p>
     * 
     * @return Un objeto {@link OpenAPI} que representa la configuración de Swagger.
     */
    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "bearerAuth";
        return new OpenAPI()
                .info(new Info().title("API Documentation")
                        .description("Documentación de la API con Swagger y Springdoc")
                        .version("1.0"))
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(new Components().addSecuritySchemes(securitySchemeName,
                        new SecurityScheme()
                                .name(securitySchemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")));
    }
}
