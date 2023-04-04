package com.shopNow.Identity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.shopNow.Identity.Entity.Customer;
import com.shopNow.Identity.dto.JwtRequestDto;
import com.shopNow.Identity.dto.JwtResponseDto;
import com.shopNow.Identity.dto.Tokens;
import com.shopNow.Identity.repository.CustomerRepository;
import com.shopNow.Identity.utility.JwtUtil;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class JwtServiceImpl implements JwtService {
	
	@Autowired
	private CustomUserDetailsService cuds;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private CustomerRepository customerRepo;

	@Override
	public JwtResponseDto createJwtTokens(JwtRequestDto jwtRequest) throws Exception {
		String userName=jwtRequest.getUserName();
		String password=jwtRequest.getPassword();
		authenticate(userName,password);
		UserDetails userDetails= cuds.loadUserByUsername(userName);
		Tokens tokens = jwtUtil.generateTokens(userDetails);
		Customer customer= customerRepo.findByUserName(userName);
		return new JwtResponseDto(customer,tokens);
	}

	private void authenticate(String userName, String password) throws Exception {
		try {
			authManager.authenticate(new UsernamePasswordAuthenticationToken(userName,password));
		}
		catch(DisabledException e) {
			log.error(System.currentTimeMillis()+" User_Disabled",e);
			 throw new Exception("USER_DISABLED", e);
		}
		catch(BadCredentialsException e) {
			log.error(System.currentTimeMillis()+" Bad_Credentials",e);
			 throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

	@Override
	public Tokens refresh(String userName) throws Exception {
		UserDetails userDetails= cuds.loadUserByUsername(userName);
		Tokens tokens = jwtUtil.generateTokens(userDetails);
		
		return tokens;
	}

}
