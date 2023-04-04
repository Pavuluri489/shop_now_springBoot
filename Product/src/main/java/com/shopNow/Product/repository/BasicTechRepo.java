package com.shopNow.Product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopNow.Product.entity.BasicSpecifications;

public interface BasicTechRepo extends JpaRepository<BasicSpecifications, Long> {

}
