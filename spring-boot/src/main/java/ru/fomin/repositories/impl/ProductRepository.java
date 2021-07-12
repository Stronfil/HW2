package ru.fomin.repositories.impl;

import org.springframework.stereotype.Component;
import ru.fomin.model.Product;
import ru.fomin.repositories.IRepository;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component("productRepository")
public class ProductRepository implements IRepository<Product, Long> {

    private Map<Long, Product> productMap;
    private static Long idCounter = 1L;

    @PostConstruct
    public void init() {
        Long currentId;
        productMap = new HashMap<>();
        productMap.put(currentId = generateId(), Product.builder()
                .id(currentId)
                .title("tomato")
                .cost(65.00)
                .description("It is very healthy red vegetable")
                .build());
        productMap.put((currentId = generateId()), Product.builder()
                .id(currentId)
                .title("bread")
                .cost(25.2)
                .description("It contains many fiber")
                .build());
        productMap.put((currentId = generateId()), Product.builder()
                .id(currentId)
                .title("milk")
                .cost(100.5)
                .description("It has many calcium and protein")
                .build());
    }

    @Override
    public List<Product> findAll() {
        return List.copyOf(productMap.values());
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productMap.containsKey(id) ? Optional.of(productMap.get(id)) : Optional.empty();
    }

    @Override
    public Long save(Product model) {
        Long id = model.getId() == null ? generateId() : model.getId();
        model.setId(id);
        productMap.put(id, model);
        return id;
    }

    @Override
    public boolean delete(Long id) {
        return productMap.remove(id) != null;
    }

    private Long generateId() {
        return idCounter++;
    }
}
