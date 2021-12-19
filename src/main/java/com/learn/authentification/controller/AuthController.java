package com.learn.authentification.controller;

import com.learn.authentification.service.AuthService;
import com.learn.authentification.request.LoginRequest;
import com.learn.authentification.response.Response;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public Flux<Response> login(@RequestBody @Valid LoginRequest req) {

        return authService.loginUserByEmail(req);
    }

}
