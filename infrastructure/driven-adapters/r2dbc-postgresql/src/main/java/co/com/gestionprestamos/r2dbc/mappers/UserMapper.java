package co.com.gestionprestamos.r2dbc.mappers;

import co.com.gestionprestamos.model.rol.Rol;
import co.com.gestionprestamos.model.user.User;
import co.com.gestionprestamos.r2dbc.entities.RolEntity;
import co.com.gestionprestamos.r2dbc.entities.UserEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserMapper {

    public UserEntity toData(User u) {
        return UserEntity.builder()
                .id(u.getId() != null ? u.getId() : UUID.randomUUID())
                .name(u.getName())
                .lastName(u.getLastName())
                .birthDate(u.getBirthDate())
                .address(u.getAddress())
                .mobileNumber(u.getMobileNumber())
                .email(u.getEmail())
                .baseSalary(u.getBaseSalary())
                .rolId(u.getRol().getId())
                .build();
    }

    public User toDomain(UserEntity e) {
        return User.builder()
                .id(e.getId())
                .name(e.getName())
                .lastName(e.getLastName())
                .birthDate(e.getBirthDate())
                .address(e.getAddress())
                .mobileNumber(e.getMobileNumber())
                .email(e.getEmail())
                .baseSalary(e.getBaseSalary())
                .rol(Rol.builder().id(e.getRolId()).build())
                .build();
    }

    public Rol toDomain(RolEntity r) {
        return Rol.builder()
                .id(r.getId())
                .name(r.getName())
                .description(r.getDescription())
                .build();
    }

}
