package com.shopNow.Cart.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopNow.Cart.entity.Cart;
import com.shopNow.Cart.service.Cartservice;

@RestController
@RequestMapping("/cart")
public class CartController {
	@Autowired
	private Cartservice cartService;

	@PostMapping("/add")
	public Map<String,Object> addToCart(@RequestParam Long productId,@RequestParam String userName) {
		Map<String,Object> map= new HashMap<>(); 
		map.put("msg", cartService.addToCart(productId,userName));
		return map;
	}
	
	@GetMapping("/getCartItems")
	public List<Cart> getCartItemsByUser(@RequestParam String userName){
		return cartService.getCartItemsByUser(userName);
	}
	
	@DeleteMapping("deleteCartItem/{id}")
	public void deleteCartItem(@PathVariable("id")Long productId) {
		cartService.deleteCartItem(productId);
	}
	
	@DeleteMapping("deleteCart/{user}")
	public void deleteCartItems(@PathVariable("user")String userName) {
		cartService.deleteCartItems(userName);
	}
}
