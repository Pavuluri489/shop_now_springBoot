package com.shopNow.Gateway.Filter;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class RouteValidator {

	public static final List<String> NonSecureEndPoints=List.of(
			"/auth/register",
			"/auth/authenticate",
			"/auth/forgotPassword",
			"/auth/changePassword",
			"/products",
			"/eureka");
	
	
	public static final List<String> Secured196527EndPoints=List.of(
			"/cart","/order","/delivery"
			);
	
	public Predicate<ServerHttpRequest> isSecured=
			request ->
				NonSecureEndPoints.stream()
				.noneMatch(uri ->request.getURI().getPath().contains(uri));
			
	
   public Predicate<ServerHttpRequest> is196527=
									request ->
                                         Secured196527EndPoints.stream()
										.anyMatch(uri ->request.getURI().getPath().contains(uri));
}
