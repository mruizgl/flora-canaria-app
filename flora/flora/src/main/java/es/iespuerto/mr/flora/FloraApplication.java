package es.iespuerto.mr.flora;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import jakarta.annotation.PostConstruct;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

@SpringBootApplication
@ImportResource("classpath:cxf-service.xml")
@OpenAPIDefinition(info = @Info(title = "Flora API", version = "1.0", description = "Demo API for Flora"))
public class FloraApplication {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(FloraApplication.class, args);
	}

	@PostConstruct
	private void initDb() {
		System.out.println(String.format("****** Creating table: %s, and Inserting test data ******", "plants"));

		// Cambiar "serial" por "auto_increment" para H2
		String sqlStatements[] = {
				"drop table if exists plants",
				"create table plants(id int auto_increment, common_name varchar(255), scientific_name varchar(255), primary key (id))",
				"insert into plants(common_name, scientific_name) values('Rosa', 'Rosa spp.')",
				"insert into plants(common_name, scientific_name) values('Tulipán', 'Tulipa spp.')"
		};

		Arrays.asList(sqlStatements).stream().forEach(sql -> {
			System.out.println(sql);
			jdbcTemplate.execute(sql);
		});

		System.out.println(String.format("****** Fetching from table: %s ******", "plants"));
		jdbcTemplate.query("select id, common_name, scientific_name from plants",
				new RowMapper<Object>() {
					@Override
					public Object mapRow(ResultSet rs, int i) throws SQLException {
						System.out.println(String.format("id:%s, common_name:%s, scientific_name:%s",
								rs.getString("id"),
								rs.getString("common_name"),
								rs.getString("scientific_name")));
						return null;
					}
				});
	}
}

