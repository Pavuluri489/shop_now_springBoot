package com.shopNow.Product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopNow.Product.entity.Product;

public interface ProductRepo extends JpaRepository<Product,Long> {

	List<Product> findBySubCategory_category_categoryName(String categoryName);

	List<Product> findBySubCategory_subCategoryName(String subCategoryName);

}
