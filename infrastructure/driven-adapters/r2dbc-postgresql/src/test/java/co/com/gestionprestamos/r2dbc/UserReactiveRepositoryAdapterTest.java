package co.com.gestionprestamos.r2dbc;

import co.com.gestionprestamos.model.rol.Rol;
import co.com.gestionprestamos.model.user.User;
import co.com.gestionprestamos.r2dbc.entities.UserEntity;
import co.com.gestionprestamos.r2dbc.mappers.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.data.domain.Example;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserReactiveRepositoryAdapterTest {

    @InjectMocks
    UserReactiveRepositoryAdapter repositoryAdapter;

    @Mock
    UserReactiveRepository repository;

    @Mock
    ObjectMapper mapper;

    @Mock
    UserMapper userMapper;

    private UserEntity userEntity;
    private final UUID ID = UUID.randomUUID();

    @BeforeEach
    void init(){
        userEntity = new UserEntity(ID, "name", "lastName", LocalDate.now(), "", "", "" , new BigDecimal("1"), null);
    }

    @Test
    void mustFindValueById() {
        when(repository.findById(ID)).thenReturn(Mono.just(userEntity));
        User u = new User(UUID.randomUUID(), "name", "", LocalDate.now(), "", "", "", new BigDecimal(1), null);
        when(userMapper.toDomain(userEntity)).thenReturn(u);

        Mono<User> result = repositoryAdapter.findById(ID);

        StepVerifier.create(result)
                .expectNextMatches(value -> value.getName().equals("name"))
                .verifyComplete();
    }

    @Test
    void mustFindAllValues() {

        User u = new User(UUID.randomUUID(), "name", "", LocalDate.now(), "", "", "", new BigDecimal(1), null);

        when(repository.findAll()).thenReturn(Flux.just(userEntity));
        when(mapper.map(userEntity, User.class)).thenReturn(u);

        Flux<User> result = repositoryAdapter.findAll();

        StepVerifier.create(result)
                .expectNextMatches(value -> value.getName().equals("name"))
                .verifyComplete();
    }

    @Test
    void mustSaveValue() {

        UUID uuid = UUID.randomUUID();
        userEntity.setRolId(uuid);
        Rol rol = new Rol(uuid, "AGENTE", "");
        User u = new User(UUID.randomUUID(), "test", "", LocalDate.now(), "", "", "", new BigDecimal(1), rol);

        when(repository.save(any())).thenReturn(Mono.just(userEntity));
        when(mapper.map(u, UserEntity.class)).thenReturn(userEntity);
        when(userMapper.toDomain(userEntity)).thenReturn(u);

        Mono<User> result = repositoryAdapter.save(u);

        StepVerifier.create(result)
                .expectNextMatches(value -> value.getName().equals("test"))
                .verifyComplete();
    }
}
