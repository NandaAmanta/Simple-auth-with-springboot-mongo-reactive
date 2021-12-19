package com.learn.authentification.controller;

import com.learn.authentification.service.AuthService;
import com.learn.authentification.request.LoginRequest;
import com.learn.authentification.request.SignUpRequest;
import com.learn.authentification.response.Response;
import com.learn.authentification.util.Validator;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public Flux<Response> login(@RequestBody @Valid LoginRequest req) {
        var resultOfValidation = Validator.validationForLoginSignUpRequest(req);

        if (!resultOfValidation.ok) {
            return Flux.just(resultOfValidation);
        }

        return authService.loginUserByEmail(req);
    }

    @PostMapping("/signup")
    public Mono<Response> signUp(@RequestBody @Valid SignUpRequest req) {
        var resultOfValidation = Validator.validationForLoginSignUpRequest(req);

        if (!resultOfValidation.ok) {
            return Mono.just(resultOfValidation);
        }

        return authService.signUpUserByEmail(req);

    }

}
