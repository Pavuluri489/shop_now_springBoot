package com.shopNow.Delivery.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {
	
	@GetMapping
	public Map<String,Object> check(){
		Map<String, Object> map=new HashMap<>();
		map.put("msg", "Welcome to Delivery Service");
		return map;
	}

}
