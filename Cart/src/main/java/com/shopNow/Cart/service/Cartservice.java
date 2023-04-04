package com.shopNow.Cart.service;

import java.util.List;

import com.shopNow.Cart.entity.Cart;

public interface Cartservice {

	String addToCart(Long productId, String userName);

	List<Cart> getCartItemsByUser(String userName);

	void deleteCartItem(Long productId);

	void deleteCartItems(String userName);

}
