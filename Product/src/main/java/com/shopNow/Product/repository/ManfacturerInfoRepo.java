package com.shopNow.Product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopNow.Product.entity.ManufaturerInfo;

public interface ManfacturerInfoRepo extends JpaRepository<ManufaturerInfo, Long> {

}
