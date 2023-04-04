package com.shopNow.Identity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopNow.Identity.Entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
