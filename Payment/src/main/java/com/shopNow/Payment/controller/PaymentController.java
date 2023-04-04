package com.shopNow.Payment.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/payment")
@RestController
public class PaymentController {

	@GetMapping
	public Map<String,Object> check() {
		Map<String,Object> map= new HashMap<>();
		map.put("msg", "Welcome to Payment Service");
		map.put("status", HttpStatus.OK);
		return map;
	}
}
