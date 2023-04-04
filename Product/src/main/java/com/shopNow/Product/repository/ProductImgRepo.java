package com.shopNow.Product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopNow.Product.entity.ProductImage;

public interface ProductImgRepo extends JpaRepository<ProductImage, Long> {

}
