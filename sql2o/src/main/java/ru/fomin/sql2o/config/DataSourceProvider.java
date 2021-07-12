package ru.fomin.sql2o.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Configuration
@PropertySource("connection.properties")
public class DataSourceProvider {

    @Value("${driver-class-name}")
    String driverClassName;

    @Value("${url}")
    String url;

    @Value("${user}")
    String username;

    @Value("${password}")
    String password;

    @PostConstruct
    public void init() {
        Flyway flyway = Flyway.configure().dataSource(dataSource()).load();
        flyway.migrate();
    }

    @Bean
    public DataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(driverClassName);
        hikariConfig.setJdbcUrl(url);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        return new HikariDataSource(hikariConfig);
    }

    @Bean
    public Sql2o sql2o(DataSource dataSource) {
        return new Sql2o(dataSource);
    }

    @Bean
    @Scope("prototype")
    public Connection connection(Sql2o sql2o) {
        return sql2o.open();
    }

}
