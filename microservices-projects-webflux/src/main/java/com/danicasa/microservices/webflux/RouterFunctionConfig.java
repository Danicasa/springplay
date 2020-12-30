package com.danicasa.microservices.webflux;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.danicasa.microservices.webflux.handler.ProjectHandler;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.beans.factory.annotation.Value;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class RouterFunctionConfig {
	
	@Value("${config.base.endpoint}")
	private String url;
	

	@Bean
	public RouterFunction<ServerResponse> routes(ProjectHandler handler){
		
		return route(GET(url), handler::list)
				.andRoute(GET(url + "/{id}"), handler::view)
				.andRoute(POST(url), handler::create)
				.andRoute(PUT(url + "{id}"), handler::edit)
				.andRoute(DELETE(url + "{id}"), handler::delete);
	}

}
