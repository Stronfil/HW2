package ru.fomin.repository.impl;

import ru.fomin.model.Product;
import ru.fomin.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProductRepositoryImpl implements ProductRepository {

    private final static int MAX_COST = 1_000_00;
    private final static List<Product> PRODUCT_LIST = new ArrayList<>();
    private final static String[] TITLE_ARR = new String[10];

    {
        TITLE_ARR[0] = "bread";
        TITLE_ARR[1] = "potato";
        TITLE_ARR[2] = "tomato";
        TITLE_ARR[3] = "fish";
        TITLE_ARR[4] = "apple";
        TITLE_ARR[5] = "meat";
        TITLE_ARR[6] = "cheese";
        TITLE_ARR[7] = "donut";
        TITLE_ARR[8] = "lemon";
        TITLE_ARR[9] = "pasta";
    }

    @Override
    public List<Product> findAllProducts() {
        fillProductList();
        return PRODUCT_LIST;
    }

    private void fillProductList(){
        if(PRODUCT_LIST.size()==10){
            return;
        }
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < 10; i++) {
            Product newProduct = Product.builder()
                    .id((long) i+1)
                    .title(TITLE_ARR[i])
                    .cost((long)random.nextInt(MAX_COST))
                    .build();
            PRODUCT_LIST.add(newProduct);
        }
    }

}
