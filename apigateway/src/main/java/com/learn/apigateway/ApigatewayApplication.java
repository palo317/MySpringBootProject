package com.learn.apigateway;

import com.learn.apigateway.filter.JWTAdminValidationFilter;
import com.learn.apigateway.filter.JWTValidationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunctions;

@SpringBootApplication
@EnableEurekaClient
public class ApigatewayApplication {

	@Autowired
	JWTValidationFilter jwtValidationFilter;

	@Autowired
	JWTAdminValidationFilter jwtAdminValidationFilter;

	public static void main(String[] args) {
		SpringApplication.run(ApigatewayApplication.class, args);
	}

	@Bean
	public RouteLocator apiLocator(RouteLocatorBuilder builder){
		return builder.routes()
				.route("user_route",
						route -> route
								.path("/users/getAll")
								.filters(filterSpec -> filterSpec.filters(jwtValidationFilter))
								.uri("lb://user-service")
				)
				.route("shop_route",
						route -> route
								.path("/cart/**")
								.filters(filterSpec -> filterSpec.filters(jwtValidationFilter))
								.uri("lb://shopping-cart-service")
				)
				.route("user_auth",
						route -> route
								.path("/users/**")
								.uri("lb://user-service")
				)
				.route("product_route",
						route -> route
								.path("/products/**")
								.filters(filterSpec -> filterSpec.filters(jwtAdminValidationFilter))
								.uri("lb://catalog-service")
				)
				.build();
	}

}
