package com.example.gatewayservice;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("patient-service-route", r -> r.path("/patient/**")
                        //.filters(f -> f.stripPrefix(1)) // Uncomment if you need to remove "/patient" from the path
                        .uri("http://localhost:8090"))
                .build();
    }
}