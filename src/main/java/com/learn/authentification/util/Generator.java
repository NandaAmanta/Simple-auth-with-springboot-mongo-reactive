/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.learn.authentification.util;

import java.util.UUID;
import org.springframework.util.DigestUtils;

/**
 *
 * @author ASUS
 */
public class Generator {

    public static String generateUserId() {
        return UUID.randomUUID().toString();
    }

    public static String generatePassword(String password) {
        return DigestUtils.md5DigestAsHex(password.getBytes());
    }
}
