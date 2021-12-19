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

    public static Response validationForLoginSignUpRequest(LoginRequest loginRequest) {

        if (loginRequest.getEmail() == null && loginRequest.getPhoneNumber() == null) {
            return new Response(false, "EMPTY_IDENTITY", "Please input your email or phone number bro", null);
        }

        //check password length greater then 8
        if (loginRequest.getPassword().length() < 8) {
            return new Response(false, "PASSWORD_NOT_VALID", "your password must be greater then 8", null);
        }

        //check valid email
        if (loginRequest.getEmail() != null
                && !loginRequest.getEmail().contains("@")
                || !loginRequest.getEmail().contains(".")) {
            return new Response(false, "EMAIL_NOT_VALID", "your email is not valid", null);

        }
        //check valid phone number
        if (loginRequest.getPhoneNumber() != null
                && loginRequest.getPhoneNumber().length() < 10) {
            return new Response(false, "PHONE_NUMBER_NOT_VALID", "your phone is not valid", null);

        }

        return new Response(true, "REQUEST_VALID", "your request is valid", null);

    }

    public static Response validationForLoginSignUpRequest(SignUpRequest signUpRequest) {

        if (signUpRequest.getEmail() == null && signUpRequest.getPhoneNumber() == null) {
            return new Response(false, "EMPTY_IDENTITY", "Please input your email or phone number bro", null);
        }

        //check password length greater then 8
        if (signUpRequest.getPassword().length() < 8) {
            return new Response(false, "PASSWORD_NOT_VALID", "your password must be greater then 8", null);
        }

        //check valid email
        if (signUpRequest.getEmail() != null
                && !signUpRequest.getEmail().contains("@")
                || !signUpRequest.getEmail().contains(".")) {
            return new Response(false, "EMAIL_NOT_VALID", "your email is not valid", null);

        }
        //check valid phone number
        if (signUpRequest.getPhoneNumber() != null
                && signUpRequest.getPhoneNumber().length() < 10) {
            return new Response(false, "PHONE_NUMBER_NOT_VALID", "your phone is not valid", null);

        }

        return new Response(true, "REQUEST_VALID", "your request is valid", null);

    }

}
