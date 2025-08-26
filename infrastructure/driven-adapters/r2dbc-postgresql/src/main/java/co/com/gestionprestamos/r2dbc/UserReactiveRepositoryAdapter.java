package co.com.gestionprestamos.r2dbc;

import co.com.gestionprestamos.model.user.User;
import co.com.gestionprestamos.model.user.gateways.UserRepository;
import co.com.gestionprestamos.r2dbc.entities.UserEntity;
import co.com.gestionprestamos.r2dbc.helper.ReactiveAdapterOperations;
import co.com.gestionprestamos.r2dbc.mappers.UserMapper;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public class UserReactiveRepositoryAdapter
        extends ReactiveAdapterOperations<
    User,
    UserEntity,
    UUID,
    UserReactiveRepository> implements UserRepository {

    private final UserMapper userMapper;

    public UserReactiveRepositoryAdapter(UserReactiveRepository repository, ObjectMapper mapper, UserMapper userMapper) {
        super(repository, mapper, d -> mapper.map(d, User.class));
        this.userMapper = userMapper;
    }

    @Override
    public Mono<Boolean> existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public Mono<User> findById(UUID id) {
        return repository.findById(id).map(userMapper::toDomain);
    }

    @Override
    @Transactional // asegura atomicidad en la operación R2DBC
    public Mono<User> save(User user) {
        UserEntity userEntity = toData(user);
        userEntity.setRolId(user.getRol().getId());
        return repository.save(userEntity).map(userMapper::toDomain);
    }
}
