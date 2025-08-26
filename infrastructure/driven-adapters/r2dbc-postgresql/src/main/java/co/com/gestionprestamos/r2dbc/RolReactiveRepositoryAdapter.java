package co.com.gestionprestamos.r2dbc;

import co.com.gestionprestamos.model.rol.Rol;
import co.com.gestionprestamos.model.rol.gateways.RolRepository;
import co.com.gestionprestamos.r2dbc.entities.RolEntity;
import co.com.gestionprestamos.r2dbc.helper.ReactiveAdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public class RolReactiveRepositoryAdapter
        extends ReactiveAdapterOperations<Rol, RolEntity, UUID, RolReactiveRepository>
        implements RolRepository {

    private final UserMapper userMapper;

    public RolReactiveRepositoryAdapter(RolReactiveRepository repository, ObjectMapper mapper, UserMapper userMapper) {
        super(repository, mapper, d -> mapper.map(d, Rol.class));
        this.userMapper = userMapper;
    }

    @Override
    public Mono<Rol> findById(UUID id) {
        return repository.findById(id).map(userMapper::toDomain);
    }
}
