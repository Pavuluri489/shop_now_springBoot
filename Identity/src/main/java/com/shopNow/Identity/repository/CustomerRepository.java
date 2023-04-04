package com.shopNow.Identity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopNow.Identity.Entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Customer findByUserName(String string);

}
