package com.vincent.inc.Saturday.fiegn;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.vincent.inc.Saturday.model.Authentication.User;

import feign.Headers;

@FeignClient("AUTHENTICATOR-SERVICE")
@Headers("Content-Type: application/json")
public interface AuthenticatorClient {

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable("id") int id);
}
