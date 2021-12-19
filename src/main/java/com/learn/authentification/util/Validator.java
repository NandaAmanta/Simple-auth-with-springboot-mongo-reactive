/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.learn.authentification.util;

import com.learn.authentification.request.LoginRequest;
import com.learn.authentification.request.SignUpRequest;
import com.learn.authentification.response.Response;

/**
 *
 * @author ASUS
 */
public class Validator {

    public Response validationForLoginSignUpRequest(LoginRequest loginRequest) {

        //check password length greater then 8
        if (loginRequest.getPassword().length() < 8) {
            return new Response(false, "PASSWORD_NOT_VALID", "your password must be greater then 8", null);
        }

        //check valid email
        if (!loginRequest.getEmail().contains("@")) {
            return new Response(false, "EMAIL_NOT_VALID", "your email is not valid", null);
        }

        return new Response(true, "REQUEST_VALID", "your request is valid", null);

    }

    public Response validationForLoginSignUpRequest(SignUpRequest signUpRequest) {

        //check password length greater then 8
        if (signUpRequest.getPassword().length() < 8) {
            return new Response(false, "PASSWORD_NOT_VALID", "your password must be greater then 8", null);
        }

        //check valid email
        if (!signUpRequest.getEmail().contains("@")) {
            return new Response(false, "EMAIL_NOT_VALID", "your email is not valid", null);
        }

        return new Response(true, "REQUEST_VALID", "your request is valid", null);

    }

}
