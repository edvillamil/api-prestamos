package co.com.gestionprestamos.usecase.getuser;

import co.com.gestionprestamos.model.user.User;
import co.com.gestionprestamos.model.user.gateways.UserRepository;
import co.com.gestionprestamos.usecase.getuser.exceptions.UserNotExistsException;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RequiredArgsConstructor
public class GetUserUseCase {

    private final UserRepository userRepository;

    public Mono<User> execute(UUID uuid) {

        return userRepository
                .findById(uuid)
                .switchIfEmpty(Mono.error(new UserNotExistsException(uuid.toString())));

    }
}
