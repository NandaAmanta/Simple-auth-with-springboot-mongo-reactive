/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.learn.authentification.service;

import com.learn.authentification.model.Auth;
import com.learn.authentification.model.User;
import com.learn.authentification.request.LoginRequest;
import com.learn.authentification.response.Response;
import com.learn.authentification.util.Generator;
import com.learn.authentification.util.Validator;
import java.util.Date;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author ASUS
 */
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AuthServiceTest {

    @Autowired
    AuthService service;

    User user;
    Auth auth;
    Response response;

    @BeforeAll
    void setUp() {
        user = new User();
        user.setCreatedAt(new Date());
        user.setEmail("tes@email.com");
        user.setPassword(Generator
                .generatePassword("passwordtesbro"));
        user.setUserId("tesId");
        user.setPhoneNumber("0899999999999");
        service.userRepo.save(user).block();
    }

    @AfterAll
    void clear() {
        service.userRepo.delete(user).block();
        service.authRepo.delete(
                service.authRepo
                        .findByUserId(user.getUserId())
                        .blockFirst()).block();
    }

    @Test
    void login_whenSuccess() {
        LoginRequest req = new LoginRequest();
        req.setEmail("tes@email.com");
        req.setPassword("passwordtesbro");
        Response result = service.loginUserByEmail(req).blockFirst();
        Assertions.assertTrue(result.ok);
    }

    @Test
    void login_whenUserNotFound() {
        LoginRequest req = new LoginRequest();
        req.setEmail("tes@email.co");
        req.setPassword("passwordtesbro");
        Response result = service.loginUserByEmail(req).blockFirst();
        Assertions.assertFalse(result.ok);
        Assertions.assertEquals("USER_NOT_FOUND", result.statusCode);
    }

    @Test
    void login_whenWrongPassword() {
        LoginRequest req = new LoginRequest();
        req.setEmail("tes@email.com");
        req.setPassword("passwordtesbros");
        Response result = service.loginUserByEmail(req).blockFirst();
        Assertions.assertFalse(result.ok);
        Assertions.assertEquals("WRONG_PASSWORD", result.statusCode);
    }

}
