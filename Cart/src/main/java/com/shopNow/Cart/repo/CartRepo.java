package com.shopNow.Cart.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopNow.Cart.entity.Cart;

public interface CartRepo extends JpaRepository<Cart, Long> {

	Cart findByProductId(Long productId);

	List<Cart> findByUserName(String userName);

	void deleteByProductId(Long productId);

	void deleteByUserName(String userName);

}
