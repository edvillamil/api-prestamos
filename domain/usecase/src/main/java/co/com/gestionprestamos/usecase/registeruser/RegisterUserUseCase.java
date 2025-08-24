package co.com.gestionprestamos.usecase.registeruser;

import co.com.gestionprestamos.model.user.User;
import co.com.gestionprestamos.model.user.gateways.UserRepository;
import co.com.gestionprestamos.model.user.validation.UserValidation;
import co.com.gestionprestamos.usecase.registeruser.exceptions.DuplicateEmailException;
import co.com.gestionprestamos.usecase.registeruser.exceptions.RegistrationValidationException;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
public class RegisterUserUseCase {

    private final UserRepository userRepository;


    public Mono<User> execute(User newUser) {
        // Validaciones de dominio
        List<String> errors = UserValidation.validateForRegistration(newUser);
        if (!errors.isEmpty()) {
            return Mono.error(new RegistrationValidationException(errors));
        }

        // Unicidad por correo
        return userRepository.existsByCorreo(newUser.getCorreoElectronico())
                .flatMap(exists -> {
                    if (exists) return Mono.error(new DuplicateEmailException(newUser.getCorreoElectronico()));
                    return userRepository.save(newUser);
                });
    }
}
