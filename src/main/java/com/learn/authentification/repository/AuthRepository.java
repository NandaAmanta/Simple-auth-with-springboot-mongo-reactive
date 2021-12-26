/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.learn.authentification.repository;

import com.learn.authentification.model.Auth;
import com.learn.authentification.model.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *
 * @author ASUS
 */
@Repository
public interface AuthRepository extends ReactiveCrudRepository<Auth, String> {

    Flux<Auth> findByToken(String token);
    Flux<Auth> findByUserId(String userId);
}
