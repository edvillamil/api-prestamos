package co.com.gestionprestamos.api.mapper;

import co.com.gestionprestamos.api.dto.RolRequest;
import co.com.gestionprestamos.api.dto.RolResponse;
import co.com.gestionprestamos.api.dto.UserRequest;
import co.com.gestionprestamos.api.dto.UserResponse;
import co.com.gestionprestamos.model.rol.Rol;
import co.com.gestionprestamos.model.user.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapperInfraestructure {

    public User toDomain(UserRequest r) {
        return User.builder()
                .name(r.getName())
                .lastName(r.getLast_name())
                .birthDate(r.getBirth_date())
                .address(r.getAddress())
                .mobileNumber(r.getMobile_number())
                .email(r.getEmail())
                .baseSalary(r.getBase_salary())
                .rol(Rol.builder().id(r.getRolId()).build())
                .build();
    }

    public UserResponse toResponse(User u) {
        return UserResponse.builder()
                .id(u.getId())
                .name(u.getName())
                .last_name(u.getLastName())
                .birth_date(u.getBirthDate())
                .address(u.getAddress())
                .mobile_number(u.getMobileNumber())
                .email(u.getEmail())
                .base_salary(u.getBaseSalary())
                .build();
    }

    public Rol toDomain(RolRequest request) {
        return Rol.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public RolResponse toResponse(Rol rol) {
        return new RolResponse(rol.getId(), rol.getName(), rol.getDescription());
    }
}
