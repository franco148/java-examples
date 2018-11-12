package com.eextreme.poc.config;

import com.eextreme.poc.feign.interceptor.SecurityFeignRequestInterceptor;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public RequestInterceptor securityFeignRequestInterceptor() {
        return new SecurityFeignRequestInterceptor();
    }
}
