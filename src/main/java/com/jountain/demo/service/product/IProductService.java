package com.jountain.demo.service.product;

import com.jountain.demo.dto.ProductDto;
import com.jountain.demo.model.Product;
import com.jountain.demo.request.ProductAddRequest;
import com.jountain.demo.request.ProductUpdateRequest;

import java.util.List;


public interface IProductService {
    Product addProduct(ProductAddRequest product);
    Product getProductById(Long id);
    void deleteProductById(Long id);

    void updateProductQuantity(int quantity, Long productId);

    Product updateProduct(ProductUpdateRequest product, Long productId);
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByBrand(String brand);
    List<Product> getProductsByCategoryAndBrand(String category, String brand);
    List<Product> getProductsByName(String name);
    List<Product> getProductsByBrandAndName(String category, String name);
    Long countProductsByBrandAndName(String brand, String name);

    List<ProductDto> getConvertedProducts(List<Product> products);

    ProductDto convertToDto(Product product);
}
