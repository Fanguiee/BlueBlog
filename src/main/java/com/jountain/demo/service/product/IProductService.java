package com.jountain.demo.service.product;

import com.jountain.demo.model.Product;

import java.util.List;

public interface IProductService {
    Product addProduct(Product product);

    Product getProductById(Long id);
    void deleteProductById(Long id);
    Product updateProduct(Product product, Long productId);

    List<Product> getAllProducts();
    List<Product> getProductsByCategoryId(String categoryId);

}
