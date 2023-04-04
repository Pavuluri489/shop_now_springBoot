package com.shopNow.Product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopNow.Product.entity.AdditionalDetails;

public interface AdditionalRepo extends JpaRepository<AdditionalDetails, Long> {

}
