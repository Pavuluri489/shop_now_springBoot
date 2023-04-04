package com.shopNow.Product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopNow.Product.entity.TechnicalDetails;

public interface TechnicalRepo extends JpaRepository<TechnicalDetails, Long> {

}
