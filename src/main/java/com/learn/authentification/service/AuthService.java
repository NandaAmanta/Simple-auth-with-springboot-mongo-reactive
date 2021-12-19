/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.learn.authentification.service;

import com.learn.authentification.model.Auth;
import com.learn.authentification.model.User;
import com.learn.authentification.repository.AuthRepository;
import com.learn.authentification.repository.UserRepository;
import com.learn.authentification.request.LoginRequest;
import com.learn.authentification.request.SignUpRequest;
import com.learn.authentification.response.Response;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *
 * @author ASUS
 */
@Service
public class AuthService {

    @Autowired
    UserRepository userRepo;

    @Autowired
    AuthRepository authRepo;

    public Flux<Response> loginUserByEmail(LoginRequest req) {
        return userRepo
                .findByEmail(req.getEmail())
                .map(result -> {
                    if (result.getPassword().equals(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()))) {
                        addAuthToken(result.getUserId());
                        return new Response(true, "SUCCESS_LOGIN", "Wellcome ,you are log in to this acount", result);
                    }
                    return new Response(false, "WRONG_PASSWORD", "your password is wrong", null);
                })
                .defaultIfEmpty(new Response(false, "USER_NOT_FOUND", "user not found by your email", null))
                .onErrorReturn(new Response(false, "INTERNAL_SERVER_ERROR", "Internal Server Error", null));
    }

    public Mono<Response> signUpUserByEmail(SignUpRequest req) {
        User newUser = new User();
        newUser.setCreatedAt(new Date());
        newUser.setEmail(req.getEmail());
        newUser.setId(UUID.randomUUID().toString());
        newUser.setPhoneNumber(req.getPhoneNumber());
        newUser.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));

        return userRepo
                .save(newUser)
                .map(result -> {
                    return new Response(true, "SUCCESS_SIGN_UP", "Success to created new user", result);
                });
    }

    public void addAuthToken(String userId) {
        var localdateTomorrow = LocalDate.now().plusDays(1).atStartOfDay();
        var expired = Date.from(localdateTomorrow.toInstant(ZoneOffset.UTC));
        var newAuthToken = new Auth();
        newAuthToken.setCreatedAt(new Date());
        newAuthToken.setUserId(userId);
        newAuthToken.setToken(UUID.randomUUID().toString());
        newAuthToken.setExpiredOn(expired);
        authRepo.save(newAuthToken).block();
    }

}
