package co.com.gestionprestamos.r2dbc;

import co.com.gestionprestamos.model.user.User;
import co.com.gestionprestamos.model.user.gateways.UserRepository;
import co.com.gestionprestamos.r2dbc.entities.UserEntity;
import co.com.gestionprestamos.r2dbc.helper.ReactiveAdapterOperations;
import co.com.gestionprestamos.r2dbc.mappers.UserMapper;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
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
    @Transactional // asegura atomicidad en la operación R2DBC
    public Mono<User> save(User user) {
        UserEntity userEntity = toData(user);
        userEntity.setRolId(user.getRol().getId());
        return repository.save(userEntity).map(userMapper::toDomain);
    }

    @Override
    public Mono<User> findByEmail(String email) {
        UserEntity userEntity = UserEntity.builder().email(email).build();
        userEntity.setEmail(email);
        Example<UserEntity> example = Example.of(userEntity);

        return repository.findOne(example).map(userMapper::toDomain);
    }


    public Flux<User> findAll() {
        return repository.findAll().map(userMapper::toDomain);
    }
}
