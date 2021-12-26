/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.learn.authentification.controller;

import com.learn.authentification.request.LoginRequest;
import com.learn.authentification.request.SignUpRequest;
import com.learn.authentification.response.Response;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *
 * @author ASUS
 */
public interface AuthOperation {

    @PostMapping("/login")
    Flux<Response> login(@RequestBody @Valid LoginRequest req);

    @PostMapping("/signup")
    Flux<Response> signUp(@RequestBody @Valid SignUpRequest req);

    @DeleteMapping("/logout")
    Flux<Response> logout(@RequestHeader("auth-token") String token);
}
