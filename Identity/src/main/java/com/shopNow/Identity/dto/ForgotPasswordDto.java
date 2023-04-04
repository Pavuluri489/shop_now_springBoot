package com.shopNow.Identity.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ForgotPasswordDto {

	private String email;
	private String userName;
	private String lastName;
	private String phNo;
	private String securityQ;
	private String answer;
}
