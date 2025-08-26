package co.com.gestionprestamos.r2dbc;

import co.com.gestionprestamos.model.rol.Rol;
import co.com.gestionprestamos.r2dbc.entities.RolEntity;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import java.util.UUID;

// TODO: This file is just an example, you should delete or modify it
public interface RolReactiveRepository
        extends ReactiveCrudRepository<RolEntity, UUID>, ReactiveQueryByExampleExecutor<RolEntity> {

}
