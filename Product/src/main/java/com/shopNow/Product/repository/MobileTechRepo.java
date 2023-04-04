package com.shopNow.Product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopNow.Product.entity.MobileSpecifications;

public interface MobileTechRepo extends JpaRepository<MobileSpecifications, Long> {

}
