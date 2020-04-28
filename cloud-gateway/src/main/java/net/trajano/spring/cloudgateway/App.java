package net.trajano.spring.cloudgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.server.adapter.ForwardedHeaderTransformer;

import java.security.Security;

@SpringBootApplication
public class App {

    @Bean
    public ForwardedHeaderTransformer forwardedHeaderTransformer() {
        return new ForwardedHeaderTransformer();
    }

//    @Bean
//    public Customizer<ReactiveResilience4JCircuitBreakerFactory> slowCustomizer() {
//        return factory -> {
//            factory.configure(builder -> builder
//                .timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(2)).build())
//                .circuitBreakerConfig(CircuitBreakerConfig.ofDefaults()), "slow", "slowflux");
//        };
//    }

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("auth_route3",
                r -> r
                    .path("/*/auth/**")
                    .filters(f -> f.stripPrefix(1))
                    .uri("http://auth:8080")
            )
            .build();
    }

    public static void main(String[] args) {
        Security.setProperty("networkaddress.cache.ttl", "1");
        SpringApplication.run(App.class, args);
    }
}
