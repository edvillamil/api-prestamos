package co.com.gestionprestamos.r2dbc;

import co.com.gestionprestamos.model.user.User;
import co.com.gestionprestamos.r2dbc.entities.UserEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserMapper {

    public UserEntity toData(User u) {
        return UserEntity.builder()
                .id(u.getId() != null ? u.getId() : UUID.randomUUID())
                .nombres(u.getNombres())
                .apellidos(u.getApellidos())
                .fechaNacimiento(u.getFechaNacimiento())
                .direccion(u.getDireccion())
                .telefono(u.getTelefono())
                .correoElectronico(u.getCorreoElectronico())
                .salarioBase(u.getSalarioBase())
                .build();
    }

    public User toDomain(UserEntity e) {
        return User.builder()
                .id(e.getId())
                .nombres(e.getNombres())
                .apellidos(e.getApellidos())
                .fechaNacimiento(e.getFechaNacimiento())
                .direccion(e.getDireccion())
                .telefono(e.getTelefono())
                .correoElectronico(e.getCorreoElectronico())
                .salarioBase(e.getSalarioBase())
                .build();
    }

}
