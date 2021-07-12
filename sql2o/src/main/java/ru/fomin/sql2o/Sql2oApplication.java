package ru.fomin.sql2o;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import ru.fomin.sql2o.config.DataSourceProvider;

@SpringBootApplication
@Import(DataSourceProvider.class)
public class Sql2oApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sql2oApplication.class, args);
	}

}
