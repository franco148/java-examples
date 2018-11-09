package com.eextreme.poc.feign.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;

@Slf4j
public class SecurityFeignRequestInterceptor implements RequestInterceptor {

    private static final String AUTHENTICATION_HEADER = "Authorization";

    @Override
    public void apply(RequestTemplate template) {

    }

    private void propagateAuthorizationHeader(RequestTemplate template) {
        if (template.headers().containsKey(AUTHENTICATION_HEADER)) {
            //log.trace("the authorization {} token has been already set", AUTHENTICATION_HEADER);
        } else {
            //log.trace("setting the authorization token {}", AUTHENTICATION_HEADER);
            template.header(AUTHENTICATION_HEADER, SecurityContextHolder.getContext().getAuthentication().getName());
        }
    }
}
