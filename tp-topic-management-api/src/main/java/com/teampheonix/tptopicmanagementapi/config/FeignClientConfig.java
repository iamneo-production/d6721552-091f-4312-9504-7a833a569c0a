package com.teampheonix.tptopicmanagementapi.config;

import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignClientConfig {

    @Bean
    public ErrorDecoder clientApiErrorDecoder() {
        return new ClientApiErrorDecoder();
    }

}
