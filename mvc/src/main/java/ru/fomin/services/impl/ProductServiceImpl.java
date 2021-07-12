package ru.fomin.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import ru.fomin.model.Product;
import ru.fomin.dao.IDao;
import ru.fomin.services.IProductService;

import java.util.List;
import java.util.Optional;

@Component
public class ProductServiceImpl implements IProductService {

    private IDao productDao;

    @Autowired
    public void setProductDao(IDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public Optional<Product> getProduct(Long id) throws HttpClientErrorException.NotFound {
        return productDao.findById(id);
    }

    @Override
    public List<Product> getProducts() {
        return productDao.findAll();
    }

    public boolean createProduct(Product product) {
        if (product.getPrice() == null ||
                product.getPrice() <= 0 ||
                product.getDescription().isEmpty() ||
                product.getTitle().isEmpty()) {
            return false;
        }
        productDao.saveOrUpdate(product);
        return true;
    }

    @Override
    public boolean updateProduct(Product product) throws HttpClientErrorException.NotFound {
        List<Product> productList = getProducts();
        if(!productList.contains(product)){
            return false;
        }
        productDao.saveOrUpdate(product);
        return true;
    }

    @Override
    public void delete(Long id) throws HttpClientErrorException.NotFound {
        productDao.delete(id);
    }
}
