 package com.shopNow.Product.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shopNow.Product.entity.Category;
import com.shopNow.Product.entity.Product;
import com.shopNow.Product.entity.ProductImage;
import com.shopNow.Product.entity.SubCategory;
import com.shopNow.Product.repository.ProductImgRepo;
import com.shopNow.Product.services.ProductService;

import jakarta.transaction.Transactional;

@RestController
@Transactional
public class ProductController {

	@Autowired
	private ProductService productService;
	@Autowired
	private ProductImgRepo productImgRepo;
	
	@GetMapping("/products/check")
	
	public ResponseEntity<Object> check() {
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body("Welcome to  view products");
	}
	@GetMapping("/834915/product")
	public ResponseEntity<Object> admin() {
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body("Welcome to create products");
	}
	
	
	@PostMapping("/834915/product/addCategory")
	public Category addCategory(@RequestPart("category") Category category,@RequestPart("categoryImage") MultipartFile file) throws IOException {
		category.setCategoryImage(this.imageUpload(file));
		return productService.addCategory(category);
	}
	
	@PostMapping("/834915/product/addSubCategory")
	public SubCategory addSubCategory(@RequestPart("subCategory") SubCategory subCategory,@RequestPart("subCategoryImage") MultipartFile file,@RequestParam String categoryName) throws IOException {
		subCategory.setSubCategoryImage(this.imageUpload(file));
		return productService.addSubCategory(subCategory,categoryName);
	}
	@PostMapping("/834915/product/addProduct")
	public Product addProduct(@RequestPart Product product,@RequestPart("profileImages") MultipartFile[] files,@RequestParam String subCategoryName) throws IOException {
		for(MultipartFile file:files) {
		product.getProductImages().add(this.imageUpload(file));
		}
		return productService.addProduct(product,subCategoryName);
	}
	
	private ProductImage imageUpload(MultipartFile file) throws IOException {
		ProductImage productImage=new ProductImage();
		productImage.setImageName(file.getOriginalFilename());
		productImage.setType(file.getContentType());
		productImage.setPicBytes(file.getBytes());
		return productImgRepo.save(productImage);
	}
	@PostMapping("/834915/product/addProductDetails")
	public Product addProductDetails(@RequestBody Product product){
		return productService.addProductDetails(product);
	}
	@GetMapping("/products/getAllCategories")
	public List<Category> getAllCategories(){
		return productService.getAllCategories();
	}
	@GetMapping("/products/getAllSubCategoriesByCategory")
	public List<SubCategory> getAllSubCategoriesByCategory(@RequestParam String category){
		return productService.getAllSubCategoriesByCategories(category);
	}
	
	@GetMapping("/products")
	public List<Product> getAllProducts(){
		return productService.getAllProducts();
	}
	@GetMapping("/products/by {Id}")
	public Product getProductById(@PathVariable("Id")Long Id){
		return productService.getProductById(Id);
	}
	@DeleteMapping("/834915/product/deleteproducts/{Id}")
	public void deleteProductById(@PathVariable("Id")Long Id){
		 productService.deleteProductById(Id);
	}
	@GetMapping("/products/{categoryName}")
	public List<Product> getProductsByCategory(@PathVariable("categoryName")String categoryName){
		return productService.getProductsByCategory(categoryName);
	}
	@GetMapping("/products/category/{subCategoryName}")
	public List<Product> getProductsBySubCategory(@PathVariable("subCategoryName")String subCategoryName){
		return productService.getProductsBySubCategory(subCategoryName);
	}
	
}
