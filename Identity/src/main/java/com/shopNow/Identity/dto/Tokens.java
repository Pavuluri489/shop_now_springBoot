package com.shopNow.Identity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Tokens {

	private String accessToken;
	private String refreshToken;
}
