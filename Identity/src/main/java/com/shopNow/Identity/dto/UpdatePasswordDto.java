package com.shopNow.Identity.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UpdatePasswordDto {

	private String userName;
	private String lastName;
	private String password;
	private String confirmPassword;
}
