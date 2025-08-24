package co.com.gestionprestamos.api;

import co.com.gestionprestamos.api.dto.UserRequest;
import co.com.gestionprestamos.api.mapper.UserMapperInfraestructure;
import co.com.gestionprestamos.usecase.registeruser.RegisterUserUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

@Slf4j
@Component
public class Handler {

    private final RegisterUserUseCase registerUser;
    private final UserMapperInfraestructure userMapper;

    public Handler(RegisterUserUseCase registerUser, UserMapperInfraestructure userMapper) {
        this.registerUser = registerUser;
        this.userMapper = userMapper;
    }

    public Mono<ServerResponse> registerUser(ServerRequest req) {
        return req.bodyToMono(UserRequest.class)
                .doOnNext(body -> log.info("Registro usuario recibido"))
                .map(userMapper::toDomain)
                .flatMap(registerUser::execute)
                .map(userMapper::toResponse)
                .flatMap(resp -> ServerResponse
                        .created(URI.create("/api/v1/usuarios/" + resp.getId()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(resp))
                .doOnSuccess(r -> log.info("[TRACE] Usuario registrado OK"))
                .doOnError(ex -> log.error("[ERROR] Fallo registro usuario: {}", ex.getMessage(), ex));

    }
}
