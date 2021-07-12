package ru.fomin.config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@org.springframework.context.annotation.Configuration
public class SessionFactoryConfig {

    @Bean
    SessionFactory sessionFactory(){
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
        return factory;
    }

    @Bean
    @Scope("prototype")
    Session session(SessionFactory sessionFactory){
        return sessionFactory.getCurrentSession();
    }


}
