package com.shopNow.Gateway.ErrorHandler;

import java.util.Map;

import org.apache.http.HttpStatus;
import org.springframework.boot.autoconfigure.web.WebProperties.Resources;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

@Component
public class ErrorHandler extends AbstractErrorWebExceptionHandler {

	public ErrorHandler(ErrorAttributes errorAttributes, Resources resources, ApplicationContext applicationContext,ServerCodecConfigurer configurer) {
		super(errorAttributes, resources, applicationContext);
		this.setMessageReaders(configurer.getReaders());
		this.setMessageWriters(configurer.getWriters());
		}

	@Override
	protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
		return RouterFunctions.route(RequestPredicates.all(),this::renderException);
	}

	
	private Mono<ServerResponse> renderException(ServerRequest request){
		Map<String,Object> error = this.getErrorAttributes(request, ErrorAttributeOptions.defaults());
		return ServerResponse.status(HttpStatus.SC_UNAUTHORIZED).contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromValue(error));
		
	}

	

}
