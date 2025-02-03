package es.iespuerto.mr.flora;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.core.JdbcTemplate;

import jakarta.annotation.PostConstruct;

import java.util.Arrays;

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

	/**
	 * Main method to run the Spring Boot application.
	 *
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(FloraApplication.class, args);
	}

	/**
	 * Method to initialize the database with tables and test data.
	 * This method is executed after the Spring context is initialized.
	 */
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
				"INSERT INTO categories(name) VALUES('Suculentas')"
		};

		Arrays.asList(sqlStatements).forEach(sql -> {
			System.out.println(sql);
			jdbcTemplate.execute(sql);
		});

		System.out.println("****** Fetching from table: characteristics ******");
		jdbcTemplate.query("SELECT id, descripcion FROM characteristics",
				(rs, rowNum) -> {
					System.out.println(String.format("id:%s, descripcion:%s",
							rs.getInt("id"),
							rs.getString("descripcion")));
					return null;
				});

		System.out.println("****** Fetching from table: locations ******");
		jdbcTemplate.query("SELECT id, name FROM locations",
				(rs, rowNum) -> {
					System.out.println(String.format("id:%s, name:%s",
							rs.getInt("id"),
							rs.getString("name")));
					return null;
				});

		System.out.println("****** Fetching from table: categories ******");
		jdbcTemplate.query("SELECT id, name FROM categories",
				(rs, rowNum) -> {
					System.out.println(String.format("id:%s, name:%s",
							rs.getInt("id"),
							rs.getString("name")));
					return null;
				});
	}


}

