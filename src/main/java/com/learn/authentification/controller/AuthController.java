package com.learn.authentification.controller;

import com.learn.authentification.model.User;
import com.learn.authentification.repository.UserRepository;
import com.learn.authentification.request.LoginRequest;
import com.learn.authentification.response.Response;
import com.mongodb.reactivestreams.client.MongoClient;
import java.util.NoSuchElementException;
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
    UserRepository userRepo;

    @PostMapping("/login")
    public Flux<Response> login(@RequestBody @Valid LoginRequest req) {

        return userRepo
                .findByEmail(req.getEmail())
                .map(result -> {
                    if (result.getPassword().equals(req.getPassword())) {
                        return new Response(true, "SUCCESS_LOGIN", "Wellcome ,you are log in to this acount", result);
                    }
                    return new Response(false, "WRONG_PASSWORD", "your password is wrong", null);
                })
                .defaultIfEmpty(new Response(false, "USER_NOT_FOUND", "user not found by your email", null));
    }

}
