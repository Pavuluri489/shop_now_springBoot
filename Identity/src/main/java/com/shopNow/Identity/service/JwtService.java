package com.shopNow.Identity.service;

import com.shopNow.Identity.dto.JwtRequestDto;
import com.shopNow.Identity.dto.JwtResponseDto;
import com.shopNow.Identity.dto.Tokens;

public interface JwtService {

	JwtResponseDto createJwtTokens(JwtRequestDto jwtRequest) throws Exception;

	Tokens refresh(String userName)throws Exception;

}
