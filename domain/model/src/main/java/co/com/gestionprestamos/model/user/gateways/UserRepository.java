package co.com.gestionprestamos.model.user.gateways;

import co.com.gestionprestamos.model.user.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface UserRepository {

    Mono<Boolean> existsByEmail(String email);
    Mono<User> save(User user);
    Mono<User> findByEmail(String email);
    Flux<User> findAll();
}
