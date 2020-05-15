package com.example.demo.space;

import com.google.common.util.concurrent.RateLimiter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class SpaceShipConfiguration {

    @Bean
    public RateLimiter rateLimiter(){
        return  RateLimiter.create(0.1d, Duration.ofSeconds(30));
    }
}
