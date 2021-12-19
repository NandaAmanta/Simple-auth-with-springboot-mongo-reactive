
package com.learn.authentification.repository;

import com.learn.authentification.model.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User,String> {

    @Override
    Flux<User> findAll();

    Flux<User> findByEmail(String email);
}
