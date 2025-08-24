package co.com.gestionprestamos.api.dto;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Value
@Builder
public class UserResponse {
    UUID id;
    String nombres;
    String apellidos;
    LocalDate fecha_nacimiento;
    String direccion;
    String telefono;
    String correo_electronico;
    BigDecimal salario_base;
}