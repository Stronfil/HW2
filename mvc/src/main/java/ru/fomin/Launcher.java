package ru.fomin;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.flywaydb.core.Flyway;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import ru.fomin.config.AppConfig;


public class Launcher {

    private static final String url = "jdbc:postgresql://localhost:5434/spring-mvc1";
    private static final String user = "postgres";
    private static final String password = "qwerty";

    public static void main(String[] args) throws Exception {

        Flyway flyway = Flyway.configure().dataSource(url, user, password).load();
        flyway.migrate();

        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(AppConfig.class);
        ServletHolder servletHolder = new ServletHolder(new DispatcherServlet(applicationContext));
        ServletContextHandler context = new ServletContextHandler();
        context.addServlet(servletHolder, "/*");
        context.setContextPath("/mvc");
        Server server = new Server(8080);
        server.setHandler(context);
        server.start();
        server.join();

    }

}
