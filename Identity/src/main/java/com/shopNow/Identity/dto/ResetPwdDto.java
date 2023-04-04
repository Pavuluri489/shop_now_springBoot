package com.shopNow.Identity.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ResetPwdDto {

	private String oldPwd;
	private String newPwd;
}
