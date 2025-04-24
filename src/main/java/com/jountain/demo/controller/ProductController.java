package com.jountain.demo.controller;

import com.jountain.demo.exceptions.ResourceNotFoundException;
import com.jountain.demo.model.Product;
import com.jountain.demo.request.AddProductRequest;
import com.jountain.demo.request.UpdateProductRequest;
import com.jountain.demo.response.ApiResponse;
import com.jountain.demo.service.product.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequiredArgsConstructor
@RequestMapping("/${api.prefix}/products")
public class ProductController {
    private final IProductService productService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(new ApiResponse("All products found.",products));
    }

    @GetMapping("/product/{productId}/product")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable Long productId) {
        try {
            Product product = productService.getProductById(productId);
            return ResponseEntity.ok(new ApiResponse("Product found.",product));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @GetMapping("/product/by/BrandAndName")
    public ResponseEntity<ApiResponse> getProductByBrandAndName(@RequestParam String productBrand, @RequestParam String productName) {
        try {
            List<Product> products = productService.getProductsByBrandAndName(productBrand,productName);
            if(products.isEmpty()) {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Products not found.",null));
            }
            else
                return ResponseEntity.ok(new ApiResponse("Products found by brand and name.",products));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }
    @GetMapping("/count/by/BrandAndName")
    public ResponseEntity<ApiResponse> countProductByBrandAndName(@RequestParam String productBrand, @RequestParam String productName) {
        try {
            var productCount = productService.countProductsByBrandAndName(productBrand,productName);
            if(productCount==0) {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Products not found.",null));
            }
            else
                return ResponseEntity.ok(new ApiResponse("Products found by brand and name.",productCount));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @GetMapping("/product/by/Category")
    public ResponseEntity<ApiResponse> getProductByCategory(@RequestParam String productCategory) {
        try {
            List<Product> products = productService.getProductsByCategory(productCategory);
            if(products.isEmpty()) {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Products not found.",null));
            }
            else
                return ResponseEntity.ok(new ApiResponse("Products found by category.",products));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @GetMapping("/product/by/CategoryAndBrand")
    public ResponseEntity<ApiResponse> getProductByCategoryAndBrand(@RequestParam String productCategory, @RequestParam String productBrand) {
        try {
            List<Product> products = productService.getProductsByCategoryAndBrand(productCategory,productBrand);
            if(products.isEmpty()) {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Products not found.",null));
            }
            else
                return ResponseEntity.ok(new ApiResponse("Products found by productCategory and brand.",products));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody AddProductRequest res) {
        try {
            productService.addProduct(res);
            return ResponseEntity.ok(new ApiResponse("Product added.",res));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @PutMapping("/update/{productId}")
    public ResponseEntity<ApiResponse> updateProduct(@RequestBody UpdateProductRequest res, @PathVariable Long productId) {
        try {
            Product P = productService.updateProduct(res, productId);
            return ResponseEntity.ok(new ApiResponse("Product updated.",P));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long productId) {
        try {
            productService.deleteProductById(productId);
            return ResponseEntity.ok(new ApiResponse("Product deleted.",null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }


}
