package co.com.gestionprestamos.model.user.gateways;

import co.com.gestionprestamos.model.user.User;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface UserRepository {

    Mono<Boolean> existsByEmail(String correo);
    Mono<User> save(User user);
    Mono<User> findById(UUID id);
}
