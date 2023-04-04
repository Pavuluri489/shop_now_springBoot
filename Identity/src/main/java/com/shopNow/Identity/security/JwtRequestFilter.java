package com.shopNow.Identity.security;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.shopNow.Identity.service.CustomUserDetailsService;
import com.shopNow.Identity.utility.JwtUtil;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
@Component
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {
	
	public static String CURRENT_USER = "";
	
	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private CustomUserDetailsService cuds;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String requestHeader = request.getHeader("Authorization");
		
		String username = null;
		String jwtToken = null;
		
		if(requestHeader!=null && requestHeader.startsWith("Bearer ")) {
			jwtToken = requestHeader.substring(7);
			try {
				username = jwtUtil.getUserNameFromToken(jwtToken);
				CURRENT_USER = username;
			}
			catch(IllegalArgumentException e){
				log.warn( new Date()+" Unable to get Jwt token");
			}
			catch(ExpiredJwtException e){
				
				log.warn( new Date()+" Jwt token got Expired");
			}
		}
		else 
			log.warn( new Date() +" JWT token does not start with Bearer");
		
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails userDetails = cuds.loadUserByUsername(username);
			if(jwtUtil.validateToken(jwtToken, userDetails)) {
				UsernamePasswordAuthenticationToken upat=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(upat);
			}
		}
		 filterChain.doFilter(request, response);
	}

}
