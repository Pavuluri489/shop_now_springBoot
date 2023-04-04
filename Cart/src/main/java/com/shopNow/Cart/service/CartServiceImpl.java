package com.shopNow.Cart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopNow.Cart.entity.Cart;
import com.shopNow.Cart.repo.CartRepo;

@Service
public class CartServiceImpl implements Cartservice {
	@Autowired
	private CartRepo cartRepo;

	@Override
	public String addToCart(Long productId, String userName) {
		 Cart cart=cartRepo.findByProductId(productId);
		 if(cart==null) {
			Cart carts=new Cart((long) (Math.random()*1000000),productId,userName);
			cartRepo.save(carts);
			return " Product Successfully Added to Cart !";
		 }
		 else 
			 return "Product Already added to cart";
	}

	@Override
	public List<Cart> getCartItemsByUser(String userName) {
		return cartRepo.findByUserName(userName);
		}

	@Override
	public void deleteCartItem(Long productId) {
	 cartRepo.deleteByProductId(productId);
	}
	
	@Override
	public void deleteCartItems(String userName) {
	 cartRepo.deleteByUserName(userName);
	}

}
