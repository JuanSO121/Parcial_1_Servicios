package co.com.vanegas.microservice.resolveEnigmaApi.config;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class Resilience4jConfig {

    @Bean
    public CircuitBreaker circuitBreaker() {
        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
                .failureRateThreshold(50) // Umbral de tasa de fallos
                .waitDurationInOpenState(Duration.ofMillis(10000)) // Duración del estado abierto
                .slidingWindowSize(100) // Tamaño de la ventana deslizante
                .build();

        return CircuitBreaker.of("myCircuitBreaker", circuitBreakerConfig);
    }
}
