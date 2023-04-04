package com.shopNow.Product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopNow.Product.entity.LaptopSpecifications;

public interface LaptopTechRepo extends JpaRepository<LaptopSpecifications, Long> {

}
