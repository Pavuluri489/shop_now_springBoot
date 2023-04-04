package com.shopNow.Identity.dto;

import com.shopNow.Identity.Entity.Customer;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponseDto {

	private Customer customer;
	private Tokens tokens;
}
