package es.iespuerto.mr.flora;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import jakarta.annotation.PostConstruct;

/**
 * Main class for the Flora Spring Boot application.
 * This class initializes the Spring Boot application and sets up the database with initial data.
 */
@SpringBootApplication
@ImportResource("classpath:cxf-service.xml")
@OpenAPIDefinition(info = @Info(title = "Flora API", version = "1.0", description = "Demo API for Flora"))
public class FloraApplication {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder; // Inyectamos el PasswordEncoder desde la configuración

    public static void main(String[] args) {
        SpringApplication.run(FloraApplication.class, args);
    }

    @PostConstruct
    private void initDb() {
        System.out.println("****** Creating tables and inserting test data ******");

        String sqlStatements[] = {
                "DROP TABLE IF EXISTS plants",
                "CREATE TABLE plants(id INT AUTO_INCREMENT, common_name VARCHAR(255), scientific_name VARCHAR(255), PRIMARY KEY (id))",
                "INSERT INTO plants(common_name, scientific_name) VALUES('Rosa', 'Rosa spp.')",
                "INSERT INTO plants(common_name, scientific_name) VALUES('Tulipán', 'Tulipa spp.')",

                "DROP TABLE IF EXISTS characteristics",
                "CREATE TABLE characteristics(id INT AUTO_INCREMENT, descripcion VARCHAR(255), PRIMARY KEY (id))",
                "INSERT INTO characteristics(descripcion) VALUES('Floración en primavera')",
                "INSERT INTO characteristics(descripcion) VALUES('Resistente a la sequía')",

                "DROP TABLE IF EXISTS locations",
                "CREATE TABLE locations(id INT AUTO_INCREMENT, name VARCHAR(255), PRIMARY KEY (id))",
                "INSERT INTO locations(name) VALUES('Teide')",
                "INSERT INTO locations(name) VALUES('La Laguna')",
                "INSERT INTO locations(name) VALUES('Santa Cruz de Tenerife')",
                "INSERT INTO locations(name) VALUES('Puerto de la Cruz')",
                "INSERT INTO locations(name) VALUES('Los Cristianos')",

                "DROP TABLE IF EXISTS categories",
                "CREATE TABLE categories(id INT AUTO_INCREMENT, name VARCHAR(255), PRIMARY KEY (id))",
                "INSERT INTO categories(name) VALUES('Árboles')",
                "INSERT INTO categories(name) VALUES('Arbustos')",
                "INSERT INTO categories(name) VALUES('Hierbas')",
                "INSERT INTO categories(name) VALUES('Flores')",
                "INSERT INTO categories(name) VALUES('Suculentas')",
                
                // Eliminar tablas si ya existen
                "DROP TABLE IF EXISTS users", 
                "DROP TABLE IF EXISTS roles",
                
                // Crear tabla de roles
                "CREATE TABLE roles (id INT AUTO_INCREMENT, name VARCHAR(255) UNIQUE, PRIMARY KEY (id))",
                
                // Crear tabla de usuarios con columna role_id (clave foránea hacia roles)
                "CREATE TABLE users (id INT AUTO_INCREMENT, name VARCHAR(255), password VARCHAR(255), role_id INT, PRIMARY KEY (id), FOREIGN KEY (role_id) REFERENCES roles(id))",
            
                // Insertar datos de ejemplo en la tabla roles
                "INSERT INTO roles(name) VALUES('ROLE_ADMIN')",
                "INSERT INTO roles(name) VALUES('ROLE_USER')",
                
                // Insertar usuarios con contraseñas cifradas usando el PasswordEncoder inyectado
                "INSERT INTO users(name, password, role_id) VALUES('Manuel', '" + passwordEncoder.encode("user123") + "', 1)", 
                "INSERT INTO users(name, password, role_id) VALUES('Pedro', '" + passwordEncoder.encode("user123") + "', 2)"
        };

        Arrays.asList(sqlStatements).forEach(sql -> {
            System.out.println(sql);
            jdbcTemplate.execute(sql);
        });

        System.out.println("****** Fetching from table: users ******");
        jdbcTemplate.query("SELECT id, name FROM users",
                (rs, rowNum) -> {
                    System.out.println(String.format("id:%s, name:%s",
                            rs.getInt("id"),
                            rs.getString("name")));
                    return null;
                });
    }
}

