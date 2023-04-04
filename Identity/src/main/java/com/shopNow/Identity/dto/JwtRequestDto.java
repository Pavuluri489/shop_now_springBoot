package com.shopNow.Identity.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class JwtRequestDto {

	private String userName;
	private String password;
}
