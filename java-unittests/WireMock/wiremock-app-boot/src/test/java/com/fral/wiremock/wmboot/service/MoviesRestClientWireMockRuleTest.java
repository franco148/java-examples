package com.fral.wiremock.wmboot.service;

import com.github.tomakehurst.wiremock.common.ConsoleNotifier;
import com.github.tomakehurst.wiremock.core.Options;
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

@SpringBootTest
@TestPropertySource(properties = {
        "moviesapp.baseUrl=http://localhost:9191"
})
public class MoviesRestClientWireMockRuleTest {

    Options options = wireMockConfig().port(9191)
            .notifier(new ConsoleNotifier(true))
            .extensions(new ResponseTemplateTransformer(true));
}
