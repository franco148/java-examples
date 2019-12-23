package com.eextreme.poc.eg01;

import org.apache.commons.codec.binary.Base64;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Arrays;

public class GetWithBasicAuthExample {

    //region Constants
    public static final String USER_NAME = "<your_user_account>";
    public static final String PASSWORD = "<your_user_password>";

    static final String URL_JIRA_SERVER = "https://springjiratest.atlassian.net";
    //endregion

    public static void main(String[] args) {
        // HttpHeaders
        HttpHeaders headers = new HttpHeaders();

        //
        // Authentication
        //
        String auth = USER_NAME+":"+PASSWORD;
        byte[] encodedAuth = Base64.decodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
        String authHeader = "Basic " + new String(encodedAuth);
        headers.set("Authorization", "Basic cm9iZXJ0X2FsXzE2XzJAaG90bWFpbC5jb206RnJhbmNvUm9iZXJ0MTQ4");

        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));

        // Request to return JSON format
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("my_other_key", "my_other_value");

        // To get result as String
        HttpEntity<Object> entity = new HttpEntity<>(headers);

        // RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // Send request with GET method, and Headers.
        ResponseEntity<Object> response = restTemplate.exchange(URL_JIRA_SERVER + "/rest/api/3/project", HttpMethod.GET, entity, Object.class);
        Object result = response.getBody();

        System.out.println(result);
    }
}
