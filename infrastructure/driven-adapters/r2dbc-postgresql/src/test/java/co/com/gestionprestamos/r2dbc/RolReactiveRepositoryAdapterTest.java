package co.com.gestionprestamos.r2dbc;

import co.com.gestionprestamos.model.rol.Rol;
import co.com.gestionprestamos.r2dbc.entities.RolEntity;
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

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RolReactiveRepositoryAdapterTest {
    // TODO: change four you own tests

    @InjectMocks
    RolReactiveRepositoryAdapter repositoryAdapter;

    @Mock
    RolReactiveRepository repository;

    @Mock
    UserMapper userMapper;

    @Mock
    ObjectMapper mapper;

    private RolEntity rolEntity;
    private Rol rol;
    private UUID uuid = UUID.randomUUID();

    @BeforeEach
    void init(){

        rolEntity = new RolEntity(uuid, "AGENTE", "DESC");

        rol = Rol.builder()
                .id(uuid)
                .name("AGENTE")
                .description("DESC")
                .build();
    }

    @Test
    void mustFindValueById() {

        when(repository.findById(uuid)).thenReturn(Mono.just(rolEntity));
        when(userMapper.toDomain(rolEntity)).thenReturn(rol);

        Mono<Rol> result = repositoryAdapter.findById(uuid);

        StepVerifier.create(result)
                .expectNextMatches(value -> value.getName().equals("AGENTE"))
                .verifyComplete();
    }

    @Test
    void mustFindAllValues() {

        when(repository.findAll()).thenReturn(Flux.just(rolEntity));
        when(mapper.map("test", Object.class)).thenReturn("test");

        Flux<Rol> result = repositoryAdapter.findAll();

        StepVerifier.create(result)
                .expectNextMatches(value -> value.getName().equals("AGENTE"))
                .verifyComplete();
    }

    @Test
    void mustSaveValue() {

        when(repository.save(any()).thenReturn(Mono.just(rol)));
        when(mapper.map("test", Object.class)).thenReturn("test");

        Mono<Rol> result = repositoryAdapter.save(new Rol(UUID.randomUUID(), "AGENTE", ""));

        StepVerifier.create(result)
                .expectNextMatches(value -> value.getName().equals("AGENTE"))
                .verifyComplete();
    }
}
