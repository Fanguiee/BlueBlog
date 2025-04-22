package com.jountain.demo.repository;

import com.jountain.demo.model.Category;
import com.jountain.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {


    Category findByName(String name);
}
