package co.com.gestionprestamos.api.mapper;

import co.com.gestionprestamos.api.dto.UserRequest;
import co.com.gestionprestamos.api.dto.UserResponse;
import co.com.gestionprestamos.model.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapperInfraestructure {

    public User toDomain(UserRequest r) {
        return User.builder()
                .nombres(r.getNombres())
                .apellidos(r.getApellidos())
                .fechaNacimiento(r.getFecha_nacimiento())
                .direccion(r.getDireccion())
                .telefono(r.getTelefono())
                .correoElectronico(r.getCorreo_electronico())
                .salarioBase(r.getSalario_base())
                .build();
    }

    public UserResponse toResponse(User u) {
        return UserResponse.builder()
                .id(u.getId())
                .nombres(u.getNombres())
                .apellidos(u.getApellidos())
                .fecha_nacimiento(u.getFechaNacimiento())
                .direccion(u.getDireccion())
                .telefono(u.getTelefono())
                .correo_electronico(u.getCorreoElectronico())
                .salario_base(u.getSalarioBase())
                .build();
    }
}
