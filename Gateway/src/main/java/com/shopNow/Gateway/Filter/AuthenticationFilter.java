package com.shopNow.Gateway.Filter;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.google.common.net.HttpHeaders;
import com.shopNow.Gateway.Exception.AccessDeniedException;
import com.shopNow.Gateway.utility.JwtUtill;

import lombok.extern.slf4j.Slf4j;
@Component
@Slf4j
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config>{

	@Autowired
	private RouteValidator validator;
	@Autowired
	private JwtUtill jwtUtill;
	
	public AuthenticationFilter() {
		super(Config.class);
	}
	
	@Override
	public GatewayFilter apply(Config config) {
		
		return ((exchange,chain)->{
			
			if(validator.isSecured.test(exchange.getRequest())) {
				if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION))
					throw new RuntimeException("Missing Authorization Header");
				String authHeader=exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
				
				if(authHeader!=null && authHeader.startsWith("Bearer "))
					authHeader=authHeader.substring(7);
				
					if(jwtUtill.getRolesFromToken(authHeader).equals("834915"))
						log.info( LocalDateTime.now()+" : Hi {},Your Role is allowed to access this page !",jwtUtill.getUserNameFromToken(authHeader));
					else if(jwtUtill.getRolesFromToken(authHeader).equals("196527") && validator.is196527.test(exchange.getRequest()))
						log.info(LocalDateTime.now()+" : Hi {}, Your Role is allowed to access this page !",jwtUtill.getUserNameFromToken(authHeader));
					else {
						log.error(LocalDateTime.now()+" : Hi {}, Your Role can't access this web Page , Access Denied !",jwtUtill.getUserNameFromToken(authHeader));
						throw new AccessDeniedException(" Hi "+jwtUtill.getUserNameFromToken(authHeader)+",Your Role can't Access this web Page,Access Denied !");
					}
					//jwtUtill.validateToken(authHeader);
				}
			return chain.filter(exchange);
		});
	}
	
	public static class Config {

		@Bean
		public WebProperties.Resources resources(){
			return new WebProperties.Resources();
		}
	}
}