package com.shopNow.Identity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopNow.Identity.dto.JwtRequestDto;
import com.shopNow.Identity.dto.JwtResponseDto;
import com.shopNow.Identity.dto.Tokens;
import com.shopNow.Identity.security.JwtRequestFilter;
import com.shopNow.Identity.service.JwtService;

@RestController
@RequestMapping("auth")
public class JwtController {

	@Autowired
	private JwtService jwtService;
	
	@PostMapping("/authenticate")
	public JwtResponseDto authenticate(@RequestBody JwtRequestDto jwtRequest) throws Exception {
		return jwtService.createJwtTokens(jwtRequest);
	}
	
	@GetMapping("/refresh")
	public Tokens refresh()throws Exception {
		return jwtService.refresh(JwtRequestFilter.CURRENT_USER);
	}
}
