package co.com.gestionprestamos.api;

import co.com.gestionprestamos.api.dto.RolRequest;
import co.com.gestionprestamos.api.dto.UserRequest;
import co.com.gestionprestamos.api.mapper.UserMapperInfraestructure;
import co.com.gestionprestamos.usecase.getuser.GetUserUseCase;
import co.com.gestionprestamos.usecase.registeruser.RegisterUserUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.UUID;

@Slf4j
@Component
public class Handler {

    private final RegisterUserUseCase registerUser;
    private final UserMapperInfraestructure userMapper;
    private final GetUserUseCase getUserUseCase;

    public Handler(RegisterUserUseCase registerUser, UserMapperInfraestructure userMapper, GetUserUseCase getUserUseCase) {
        this.registerUser = registerUser;
        this.userMapper = userMapper;
        this.getUserUseCase = getUserUseCase;
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

    public Mono<ServerResponse> getUser(ServerRequest req) {

        UUID uuid = UUID.fromString(req.pathVariable("id"));
        return getUserUseCase.execute(uuid)
                .doOnNext( body -> log.info("Solicitud consulta ususario: " + uuid ))
                .map(userMapper::toResponse)
                .flatMap( u-> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(u))
                        .switchIfEmpty(ServerResponse.notFound().build()));

    }
}
