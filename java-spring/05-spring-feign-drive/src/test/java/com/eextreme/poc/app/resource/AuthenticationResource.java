package com.eextreme.poc.app.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationResource {

    public ResponseEntity<String> getAuthorization(@RequestHeader("Authorization") String authorization) {

        return ResponseEntity.ok().header("Authorization", authorization).body(authorization);
    }
}
