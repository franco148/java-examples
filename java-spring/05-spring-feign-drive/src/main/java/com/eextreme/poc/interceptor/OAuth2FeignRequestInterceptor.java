package com.eextreme.poc.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.util.Assert;

/**
 * The OAuth2 authentication request interceptor. It populates the {@code Authorization} header of any remote service
 * call request based on the current {@link OAuth2ClientContext} by setting the bearer access token.
 *
 */
public class OAuth2FeignRequestInterceptor implements RequestInterceptor {

    //region Properties
    /**
     * The logger instance used by this class.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(OAuth2FeignRequestInterceptor.class);

    /**
     * The authorization header name.
     */
    private static final String AUTHORIZATION_HEADER = "Authorization";

    /**
     * The {@code Bearer} token type.
     */
    private static final String BEARER_TOKEN_TYPE = "Bearer";


    /**
     * Current OAuth2 authentication context.
     */
    private final OAuth2ClientContext oAuth2ClientContext;
    //endregion

    //region Constructors

    /**
     * Creates new instance of {@link OAuth2FeignRequestInterceptor} with client context.
     *
     * @param oAuth2ClientContext the OAuth2 client context
     */
    public OAuth2FeignRequestInterceptor(OAuth2ClientContext oAuth2ClientContext) {
        Assert.notNull(oAuth2ClientContext, "Context can not be null");
        this.oAuth2ClientContext = oAuth2ClientContext;
    }

    //endregion

    //region Overrides
    /**
     * {@inheritDoc}
     */
    @Override
    public void apply(RequestTemplate requestTemplate) {

        if (requestTemplate.headers().containsKey(AUTHORIZATION_HEADER)) {
            LOGGER.warn("The Authorization token has been already set");
        } else if (oAuth2ClientContext.getAccessTokenRequest().getExistingToken() == null) {
            LOGGER.warn("Can not obtain existing token for request, if it is a non secured request, ignore.");
        } else {
            LOGGER.debug("Constructing Header {} for Token {}", AUTHORIZATION_HEADER, BEARER_TOKEN_TYPE);
            requestTemplate.header(AUTHORIZATION_HEADER, String.format("%s %s",
                    BEARER_TOKEN_TYPE,
                    oAuth2ClientContext.getAccessTokenRequest().getExistingToken().toString()));
        }
    }
    //endregion
}
