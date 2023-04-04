package com.shopNow.Product.services;

import java.util.List;

import com.shopNow.Product.entity.Category;
import com.shopNow.Product.entity.Product;
import com.shopNow.Product.entity.SubCategory;

public interface ProductService {

	Product addProduct(Product product, String subCategoryName);

	Category addCategory(Category category);

	SubCategory addSubCategory(SubCategory subCategory, String categoryName);

	List<Product> getAllProducts();

	List<Product> getProductsByCategory(String categoryName);

	List<Product> getProductsBySubCategory(String subCategoryName);

	List<Category> getAllCategories();

	List<SubCategory> getAllSubCategoriesByCategories(String category);

	Product getProductById(Long id);

	void deleteProductById(Long id);

	Product addProductDetails(Product product);

}
