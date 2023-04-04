package com.shopNow.Product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopNow.Product.entity.SubCategory;

public interface SubCategoryRepo extends JpaRepository<SubCategory, Long> {

	SubCategory findBySubCategoryName(String subCategoryName);

	List<SubCategory> findByCategory_categoryName(String category);

}
