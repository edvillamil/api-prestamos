package co.com.gestionprestamos.usecase.getuser;

import co.com.gestionprestamos.model.user.User;
import co.com.gestionprestamos.model.user.gateways.UserRepository;
import co.com.gestionprestamos.usecase.getuser.exceptions.UserNotExistsException;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class GetUserUseCase {

    private final UserRepository userRepository;

    public Mono<User> execute(String email) {

        return userRepository
                .findByEmail(email)
                .switchIfEmpty(Mono.error(new UserNotExistsException(email)));
    }
}
