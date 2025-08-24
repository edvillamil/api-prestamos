package co.com.gestionprestamos.r2dbc;

import co.com.gestionprestamos.r2dbc.entities.UserEntity;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface UserReactiveRepository
        extends ReactiveCrudRepository<UserEntity, UUID>, ReactiveQueryByExampleExecutor<UserEntity> {

    Mono<Boolean> existsByCorreoElectronico(String correoElectronico);
}
