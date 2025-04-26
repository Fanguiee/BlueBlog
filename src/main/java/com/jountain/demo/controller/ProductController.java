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

    @GetMapping("/product/{id}/product")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable Long id) {
        try {
            Product product = productService.getProductById(id);
            return ResponseEntity.ok(new ApiResponse("Product found.",product));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @GetMapping("/product/by/brand")
    public ResponseEntity<ApiResponse> getProductByBrand(@RequestParam String brand) {
        try {
            List<Product> products = productService.getProductsByBrand(brand);
            if(products.isEmpty()) {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Products not found.",null));
            }
            else
                return ResponseEntity.ok(new ApiResponse("Products found by brand.",products));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }
    @GetMapping("/product/by/brandAndName")
    public ResponseEntity<ApiResponse> getProductByBrandAndName(@RequestParam String brand, @RequestParam String name) {
        try {
            List<Product> products = productService.getProductsByBrandAndName(brand, name);
            if(products.isEmpty()) {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Products not found.",null));
            }
            else
                return ResponseEntity.ok(new ApiResponse("Products found by brand and name.",products));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }
    @GetMapping("/count/by/brandAndName")
    public ResponseEntity<ApiResponse> countProductByBrandAndName(@RequestParam String brand, @RequestParam String name) {
        try {
            var productCount = productService.countProductsByBrandAndName(brand, name);
            if(productCount==0) {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Products not found.",null));
            }
            else
                return ResponseEntity.ok(new ApiResponse("Products found by brand and name.",productCount));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @GetMapping("/product/by/category")
    public ResponseEntity<ApiResponse> getProductByCategory(@RequestParam String category) {
        try {
            List<Product> products = productService.getProductsByCategory(category);
            if(products.isEmpty()) {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Products not found.",null));
            }
            else
                return ResponseEntity.ok(new ApiResponse("Products found by category.",products));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @GetMapping("/product/by/categoryAndBrand")
    public ResponseEntity<ApiResponse> getProductByCategoryAndBrand(@RequestParam String category, @RequestParam String brand) {
        try {
            List<Product> products = productService.getProductsByCategoryAndBrand(category,brand);
            if(products.isEmpty()) {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Products not found.",null));
            }
            else
                return ResponseEntity.ok(new ApiResponse("Products found by category and brand.",products));
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

    @PutMapping("/product/update/{id}")
    public ResponseEntity<ApiResponse> updateProduct(@RequestBody UpdateProductRequest res, @PathVariable Long id) {
        try {
            Product P = productService.updateProduct(res, id);
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
