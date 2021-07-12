package ru.fomin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.fomin.repository.ProductRepository;
import ru.fomin.repository.impl.ProductRepositoryImpl;
import ru.fomin.service.ProductService;
import ru.fomin.service.impl.ProductServiceImpl;

@Configuration
@ComponentScan("ru.fomin")
public class AppConfig {

    @Bean
    public ProductRepository productRepository() {
        return new ProductRepositoryImpl();
    }

    @Bean
    public ProductService productService(ProductRepository productRepository) {
        return new ProductServiceImpl(productRepository);
    }

}
